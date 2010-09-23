package intercept.logging;

import intercept.proxy.HTTPRequest;
import intercept.utils.EventTimer;

import java.net.Socket;

public interface EventLogger {
    void logRequest(Socket socket, HTTPRequest request);

    void appendResponse(Socket socket, EventTimer timer, int responseLength, HTTPRequest request);

    void logError(String message);

    void logError(String message, Exception exception);

    void logResponseData(byte[] request, byte[] response);

    void logSubbedResponse(String address, int port, HTTPRequest request, String message);
}
