package intercept.logging;

import intercept.model.LogElement;
import intercept.model.LogEntry;
import intercept.model.SimpleLogElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventLog {
    private List<LogEntry> entries = Collections.synchronizedList(new ArrayList<LogEntry>());
    private LogEntry current;

    public List<LogEntry> getEntries() {
        List<LogEntry> sorted = new ArrayList<LogEntry>(entries);
        Collections.sort(sorted);
        return sorted;
    }

    public void clear() {
        entries.clear();
    }

    public void add(LogEntry logEntry) {
        entries.add(logEntry);
        current = logEntry;
    }

    public void append(LogElement element) {
        if (current == null) {
            LogEntry logEntry = new LogEntry();
            logEntry.addElement(new SimpleLogElement("Orphaned entry"));
            add(logEntry);
        }
        current.addElement(element);
    }
}
