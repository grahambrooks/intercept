package intercept.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class LogEntryUnitTests {
    @Test
    public void logEntriesAreComparedOnTime() {
        LogEntry one = new LogEntry(123, null);
        LogEntry two = new LogEntry(123, null);

        assertThat(one.compareTo(two), equalTo(0));
    }

    @Test
    public void logEntryEqualityConsidersElementContents() {
        LogEntry one = new LogEntry(123, null);
        LogEntry two = new LogEntry(123, null);

        assertThat(one.equals(two), equalTo(false));
    }
}
