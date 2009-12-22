package intercept.logging;

import intercept.model.LogEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventLog {
    List<LogEntry> entries = Collections.synchronizedList(new ArrayList<LogEntry>());

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
    }
}
