package intercept.logging;

import intercept.model.ErrorLogElement;
import intercept.model.LogEntry;
import intercept.model.RequestLogElement;
import intercept.model.ResponseDataLogElement;
import intercept.model.ResponseLogElement;
import intercept.model.StubbedResponseLogElement;
import intercept.proxy.HTTPRequest;
import intercept.utils.EventTimer;

import java.net.Socket;

public class EventLogger {
    private final EventLog log;

    public EventLogger(EventLog log) {
        this.log = log;
    }

    public void logRequest(Socket socket, HTTPRequest request) {
        LogEntry logEntry = new LogEntry();
        logEntry.addElement(new RequestLogElement(socket.getInetAddress().getHostAddress(), socket.getLocalPort(), request));
        log.add(logEntry);
    }

    public void appendResponse(Socket socket, EventTimer timer, int responseLength, HTTPRequest request) {
        log.append(new ResponseLogElement(socket.getInetAddress().getHostAddress(), socket.getLocalPort(), timer, responseLength, request));
    }

    public void logError(String message) {
        log.append(new ErrorLogElement(message, null));
    }

    public void logError(String message, Exception exception) {
        log.append(new ErrorLogElement(message, exception));
    }

    public void logResponseData(byte[] request, byte[] response) {
        log.append(new ResponseDataLogElement(request, response));
    }

    public void logSubbedResponse(String address, int port, HTTPRequest request, String message) {
        log.append(new StubbedResponseLogElement(address, port, request, message));
    }
}
