package intercept.proxy;

import intercept.configuration.ProxyConfig;
import intercept.logging.ApplicationLog;
import intercept.logging.EventLogger;
import intercept.utils.EventTimer;
import intercept.utils.MillisecondTimer;
import intercept.utils.Utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.Socket;

import static intercept.utils.Utils.close;

public class ProxyChannel extends Thread {
    private Socket socket;
    private ProxyConfig config;
    private EventLogger logger;
    private ApplicationLog applicationLog;

    public ProxyChannel(Socket socket, ProxyConfig config, EventLogger logger, ApplicationLog applicationLog) {
        this.socket = socket;
        this.config = config;
        this.logger = logger;
        this.applicationLog = applicationLog;
    }

    public void run() {
        BufferedInputStream clientIn = null;
        BufferedOutputStream clientOutputStream = null;
        try {
            EventTimer timer = new MillisecondTimer();

            clientIn = new BufferedInputStream(socket.getInputStream());
            clientOutputStream = new BufferedOutputStream(socket.getOutputStream());

            // other variables

            // get the header info (the web browser won't disconnect after
            // it's sent a request, so make sure the waitForDisconnect
            // parameter is false)
            HTTPRequest request = new HTTPRequest();
            getHTTPData(clientIn, request, false);

            logger.logRequest(socket, request);

            config.transformRoute(request);

            if (config.stubbedRequest(request.url())) {
                handleStubbedResponse(clientOutputStream, request);
            } else {
                processResponse(clientOutputStream, timer, request);
            }
        } catch (IOException e) {
            logger.logError("Error in ProxyThread: ", e);
        } finally {
            close(clientOutputStream);
            close(clientIn);
            close(socket);
        }
    }

    private void processResponse(BufferedOutputStream clientOutputStream, EventTimer timer, HTTPRequest request) throws IOException {
        Socket outboundSocket = openOutboundSocket(clientOutputStream, request);

        if (outboundSocket != null) {
            InputStream outboundInputStream = new BufferedInputStream(outboundSocket.getInputStream());
            BufferedOutputStream outboundOutputStream = new BufferedOutputStream(outboundSocket.getOutputStream());

            request.addConnectionCloseHeader();
            request.write(outboundOutputStream);
            outboundOutputStream.flush();

            int responseLength;

            if (config.getDebugLevel() > 1) {
                byte[] response = getHTTPData(outboundInputStream, true);
                responseLength = Array.getLength(response);
                clientOutputStream.write(response, 0, Array.getLength(response));
                if (config.getDebugLevel() > 1) {
                    logResponseData(request.requestData, response);
                }
            } else {
                responseLength = streamHTTPData(outboundInputStream, clientOutputStream, true);
            }

            if (config.getDebugLevel() > 0) {
                logResponse(timer, responseLength, request);
            }

            close(outboundInputStream);
            close(outboundOutputStream);
        }
    }

    private Socket openOutboundSocket(BufferedOutputStream clientOutputStream, HTTPRequest request) {
        try {
            if (config.hasOutgoingProxyFor(request.hostName())) {
                return new Socket(config.getOutgoingProxyHost(), config.getOutgoingProxyPort());
            } else {
                return new Socket(request.hostName(), request.hostPortNumber());
            }
        } catch (IOException e) {
            sendClientErrorMessage(clientOutputStream, request, e);
        }
        return null;
    }

    private void sendClientErrorMessage(BufferedOutputStream clientOutputStream, HTTPRequest request, IOException e) {
        // tell the client there was an error
        String errMsg = "HTTP/1.0 500\nContent Type: text/plain\n\n"
                + "Error connecting to the remoteServerSocket:\n"
                + " host " + request.hostName() + "\n"
                + " port " + request.hostPortNumber() + "\n"
                + "Error "
                + e;
        try {
            clientOutputStream.write(errMsg.getBytes(), 0, errMsg.length());
        } catch (IOException e1) {
            throw new RuntimeException(e1);
        }
    }

    private void logResponseData(byte[] request, byte[] response) {
        logger.logResponseData(request, response);
    }

    protected void logResponse(EventTimer timer, int responseLength, HTTPRequest request) {
        logger.appendResponse(socket, timer, responseLength, request);
    }

    private void handleStubbedResponse(BufferedOutputStream clientOut, HTTPRequest request) throws IOException {
        String stubbedResponse = config.getStubbedResponse(request.url());
        String message = "HTTP/1.0 200\r\n"
                + "Content-Type: text/plain\r\n"
                + "Content-Encoding: UTF-8\r\n"
                + "Content-Length: " + stubbedResponse.length() + "\r\n"
                + "\r\n"
                + stubbedResponse;

        logger.logSubbedResponse(socket.getInetAddress().getHostAddress(), socket.getLocalPort(), request, message);

        byte[] messageBytes = message.getBytes();
        clientOut.write(messageBytes, 0, messageBytes.length);
    }


    private byte[] getHTTPData(InputStream in, boolean waitForDisconnect) {
        // get the HTTP data from an InputStream, and return it as
        // a byte array
        // the waitForDisconnect parameter tells us what to do in case
        // the HTTP header doesn't specify the Content-Length of the
        // transmission
        HTTPRequest request = new HTTPRequest();
        getHTTPData(in, request, waitForDisconnect);
        return request.requestData;
    }


    private void getHTTPData(InputStream in, HTTPRequest request, boolean waitForDisconnect) {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        streamHTTPData(in, bs, request, waitForDisconnect);
        request.requestData = bs.toByteArray();
    }


    private int streamHTTPData(InputStream in, OutputStream out, boolean waitForDisconnect) {
        return streamHTTPData(in, out, new HTTPRequest(), waitForDisconnect);
    }

    private int streamHTTPData(InputStream in,
                               OutputStream out,
                               HTTPRequest request,
                               boolean waitForDisconnect) {
        // get the HTTP data from an InputStream, and send it to
        // the designated OutputStream
        StringBuilder header = new StringBuilder("");
        int responseCode;
        int transferredBytes = 0;

        try {
            responseCode = readHeader(in, request, header);

            request.setHeaderData(header.toString().getBytes());
            // add a blank line to terminate the header info
            header.append("\r\n");

            // convert the header to a byte array, and write it to our stream
            out.write(header.toString().getBytes(), 0, header.length());

            // if the header indicated that this was not a 200 response,
            // just return what we've got if there is no Content-Length,
            // because we may not be getting anything else
            if ((responseCode != 200) && (request.contentLength == 0)) {
                out.flush();
                return header.length();
            }

            if (request.contentLength > 0) {
                waitForDisconnect = false;
            }

            transferredBytes = copyResponse(in, out, new StreamControl(request.contentLength, waitForDisconnect));
        } catch (IOException e) {
            logger.logError("Error getting HTTP data: ", e);
        }

        //flush the OutputStream and return
        try {
            out.flush();
        } catch (Exception e) {
            logger.logError("Error flushing output stream ", e);
        }
        return (header.length() + transferredBytes);
    }

    private int readHeader(InputStream in, HTTPRequest httpRequest, StringBuilder header) {
        int responseCode = 200;
        String data;

        try {
            data = Utils.readLine(in);
            if (data != null) {
                httpRequest.updatePath(data);
                header.append(data).append("\r\n");
                responseCode = parseResponseCode(data);
            }

            while ((data = Utils.readLine(in)) != null) {
//                trace("readHeader", data);
                // the header ends at the first blank line
                if (data.length() == 0)
                    break;
                header.append(data).append("\r\n");

                httpRequest.updateHost(data);
                httpRequest.updateContentLength(data);
                httpRequest.updatePath(data);
            }
        } catch (IOException e) {
            logger.logError("Error getting header", e);
        }

        return responseCode;
    }

    private int copyResponse(InputStream inputStream, OutputStream outputStream, StreamControl streamControl) {
        int totalBytesReceived = 0;
        if (streamControl.dataExpected()) {
            trace("copyResponse", streamControl.toString());
            try {
                byte[] buf = new byte[40960];
                int bufferedByteCount = 0;
                while (streamControl.moreDataExpected(totalBytesReceived) && !eof(bufferedByteCount)) {
                    bufferedByteCount = inputStream.read(buf);
                    if (!eof(bufferedByteCount)) {
                        outputStream.write(buf, 0, bufferedByteCount);
                        totalBytesReceived += bufferedByteCount;
                    }
                    trace("copyResponse", "read " + totalBytesReceived + " of " + streamControl.expected());
                }
            } catch (IOException e) {
                logger.logError("Error getting HTTP body: ", e);
            }
        }
        trace("copyResponse", "complete");
        return totalBytesReceived;
    }

    private boolean eof(int bytesIn) {
        return bytesIn < 0;
    }


    private int parseResponseCode(String data) {
        int pos;
        pos = data.indexOf(" ");
        if ((data.toLowerCase().startsWith("http")) && (pos >= 0) && (data.indexOf(" ", pos + 1) >= 0)) {

            String rcString = data.substring(pos + 1, data.indexOf(" ", pos + 1));
            try {
                trace("parseResponseCode", "Response code " + rcString);
                return Integer.parseInt(rcString);
            } catch (NumberFormatException e) {
                logger.logError("Error parsing response code " + rcString);
            }
        }
        return 200;
    }

    private void trace(String method, String message) {
        if (config.getDebugLevel() > 1) {
            applicationLog.trace(Thread.currentThread().getId() + " " + method + ": " + message);
        }
    }

}
