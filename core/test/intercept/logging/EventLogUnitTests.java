package intercept.logging;

import intercept.model.LogEntry;
import intercept.model.SimpleLogElement;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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
