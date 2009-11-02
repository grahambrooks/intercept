package intercept.logging;

import intercept.model.LogElement;
import intercept.model.LogEntry;

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
        return new LogElement(buffer.toString());
    }

    public void log(LogElement... elements) {
        log.add(new LogEntry(elements));
    }

    public void log(long creationTime, LogElement... elements) {
        log.add(new LogEntry(creationTime, elements));
    }
}
