package intercept.logging;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import intercept.model.LogEntry;

import org.junit.Test;

public class EventLogUnitTests {
    @Test
    public void eventLogStartsEmpty() {
        EventLog log = new EventLog();

        assertThat(log.getEntries().size(), is(0));
    }

    @Test
    public void canClearEventLogEntries() {
        EventLog log = new EventLog();
        log.add(new LogEntry());

        assertThat(log.getEntries().size(), is(1));
        log.clear();
        assertThat(log.getEntries().size(), is(0));
    }
}
