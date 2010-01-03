package intercept.logging;

import intercept.model.LogElement;
import intercept.model.LogEntry;
import intercept.model.RequestLogElement;
import intercept.model.ResponseLogElement;
import intercept.model.SimpleLogElement;
import intercept.proxy.HTTPRequest;
import intercept.utils.EventTimer;

import java.net.Socket;

public class EventLogger {
    private EventLog log;

    public EventLogger(EventLog log) {
        this.log = log;
    }

    public static LogElement e(Object... message) {
        StringBuilder buffer = new StringBuilder();
        for (Object messageElement : message) {
            buffer.append(messageElement);
        }
        return new SimpleLogElement(buffer.toString());
    }

    public void log(LogElement... elements) {
        log.add(new LogEntry(elements));
    }

    public LogEntry logRequest(Socket socket, HTTPRequest request) {
        LogEntry logEntry = new LogEntry();
        logEntry.addElement(new RequestLogElement(socket.getInetAddress().getHostAddress(), socket.getLocalPort(), request));
        return logEntry;
    }

    public void appendResponse(Socket socket, EventTimer timer, int responseLength, HTTPRequest request) {
        log.append(new ResponseLogElement(socket.getInetAddress().getHostAddress(), socket.getLocalPort(), timer, responseLength, request));
    }
}
