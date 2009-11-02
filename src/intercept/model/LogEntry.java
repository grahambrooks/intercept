package intercept.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogEntry implements Comparable {
    public List<LogElement> elements;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss:SSS");
    private long creationTime;

    public LogEntry(LogElement[] text) {
        this(System.nanoTime(), text);
    }

    public LogEntry(long creationTime, LogElement[] logElements) {
        this.creationTime = creationTime;
        this.elements = new ArrayList<LogElement>();
        this.elements.add(new LogElement(dateFormat.format(new Date())));

        if (logElements != null) {
            for (LogElement element : logElements) {
                this.elements.add(element);
            }
        }
    }

    List<LogElement> getElements() {
        return elements;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass().isInstance(obj)) {
            LogEntry other = (LogEntry) obj;
            if (other.creationTime != creationTime || elements.size() != other.elements.size()) {
                return false;
            }

            for (int i = 0; i < elements.size(); i++) {
                if (!elements.get(i).equals(other.elements.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        assert false : "Hashcode not supported for LogEntry";
        return 42;
    }

    public int compareTo(Object o) {
        LogEntry other = (LogEntry) o;
        return (int) (this.creationTime - other.creationTime);
    }
}
