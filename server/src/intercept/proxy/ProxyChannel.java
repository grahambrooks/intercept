package intercept.proxy;

import intercept.configuration.ProxyConfig;
import intercept.logging.ApplicationLog;
import intercept.logging.EventLogger;
import static intercept.logging.EventLogger.e;
import intercept.server.StreamControl;
import intercept.utils.EventTimer;
import intercept.utils.Utils;
import static intercept.utils.Utils.close;

import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;

public class ProxyChannel extends Thread {
    private Socket socket;
    private ProxyConfig config;
    private EventLogger logger;
    private ApplicationLog applicationLog;
    private long creationTime;

    public static final int DEFAULT_TIMEOUT = 2 * 1000;

    public ProxyChannel(Socket socket, ProxyConfig config, EventLogger logger, ApplicationLog applicationLog) {
        this.socket = socket;
        this.config = config;
        this.logger = logger;
        this.applicationLog = applicationLog;
        creationTime = System.nanoTime();
    }

    public void run() {
        BufferedInputStream clientIn = null;
        BufferedOutputStream clientOutputStream = null;
        try {
            EventTimer timer = new EventTimer();

            clientIn = new BufferedInputStream(socket.getInputStream());
            clientOutputStream = new BufferedOutputStream(socket.getOutputStream());

            // other variables

            // get the header info (the web browser won't disconnect after
            // it's sent a request, so make sure the waitForDisconnect
            // parameter is false)
            HTTPRequest request = new HTTPRequest();
            getHTTPData(clientIn, request, false);

            logger.log(creationTime,
                    e(socket.getInetAddress().getHostAddress() + ":" + socket.getLocalPort()),
                    e("->"),
                    e(request.hostName() + ":" + request.hostPortNumber()),
                    e(request.getPath()),
                    e(request.length() + "&nbsp;bytes")
            );

            config.transformRoute(request);

            if (config.stubbedRequest(request.hostName())) {
                handleStubbedResponse(clientIn, clientOutputStream, request);
                return;
            }

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
        } catch (IOException e) {
            if (config.getDebugLevel() > 0)
                logger.log(e("Error in ProxyThread: " + e));
        } finally {
            close(clientOutputStream);
            close(clientIn);
            close(socket);
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
        logger.log(
                e("REQUEST:\n" + (new String(request))),
                e("RESPONSE:\n" + (new String(response != null ? response : new byte[0])))
        );

    }

    protected void logResponse(EventTimer timer, int responseLength, HTTPRequest request) {
        logger.log(
                e("Request from ", socket.getInetAddress().getHostAddress(), ":", socket.getLocalPort()),
                e(request.hostName(), ":", request.hostPortNumber()),
                e(request.length(), " bytes sent"),
                e(responseLength, " bytes returned"),
                e(timer)
        );
    }

    private void handleStubbedResponse(BufferedInputStream clientIn, BufferedOutputStream clientOut, HTTPRequest request) throws IOException {
        String stubbedResponse = config.getStubbedResponse(request.hostName());
        String message = "HTTP/1.0 200\nContent Type: text/plain\nContent-Encoding: UTF-8\nContent Length: " + stubbedResponse.length() + "\n\n" + stubbedResponse;
        logger.log(
                e("Request from ", socket.getInetAddress().getHostAddress() + ":" + socket.getLocalPort()),
                e("STUBBED"),
                e(request.length() + " bytes sent"),
                e(stubbedResponse.length() + " bytes returned"),
                e(message)
        );
        clientOut.write(message.getBytes(), 0, message.length());
        close(clientIn);
        close(clientOut);
        close(socket);
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
        // get the HTTP data from an InputStream, and return it as
        // a byte array, and also return the Host entry in the header,
        // if it's specified -- note that we have to use a StringBuffer
        // for the 'host' variable, because a String won't return any
        // information when it's used as a parameter like that
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
            if (config.getDebugLevel() > 0) {
                logger.log(e("Error getting HTTP data: " + e));
            }
        }

        //flush the OutputStream and return
        try {
            out.flush();
        } catch (Exception e) {
            logger.log(e("Error flushing output stream ", e));
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
            if (config.getDebugLevel() > 0) {
                logger.log(e("Error getting header: " + e));
            }
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
                String errMsg = "Error getting HTTP body: " + e;
                if (config.getDebugLevel() > 0) {
                    logger.log(e(errMsg));
                }
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
                if (config.getDebugLevel() > 0) {
                    logger.log(e("Error parsing response code " + rcString));
                }
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
