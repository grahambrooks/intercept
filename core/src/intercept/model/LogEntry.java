package intercept.model;

import intercept.utils.Clock;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class LogEntry implements Comparable<Object> {
    public List<LogElement> elements;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss:SSS");

    public LogEntry() {
        this(null);
    }

    public LogEntry(LogElement[] logElements) {
        this.elements = new ArrayList<LogElement>();

        if (logElements != null) {
            for (LogElement element : logElements) {
                addElement(element);
            }
        }
    }

    public void addElement(LogElement element) {
        element.setTime(Clock.nanoTime());
        elements.add(element);
    }

    List<LogElement> getElements() {
        return elements;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass().isInstance(obj)) {
            LogEntry other = (LogEntry) obj;
            if (elements.size() != other.elements.size()) {
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
        throw new UnsupportedOperationException("Hashcode not supported for LogEntry");
    }

    public long earliestElementTime() {
        if (elements.size() > 0) {
            return elements.get(0).time();
        }

        return 0xFFFFFFFF;
    }

    public int compareTo(Object o) {
        LogEntry other = (LogEntry) o;
        return (int) (this.earliestElementTime() - other.earliestElementTime());
    }
}
