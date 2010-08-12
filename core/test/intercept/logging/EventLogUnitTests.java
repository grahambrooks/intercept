package intercept.logging;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import intercept.model.By;
import intercept.model.LogElement;
import intercept.model.LogEntry;

import intercept.model.LogFilter;
import intercept.model.SimpleLogElement;
import org.junit.Test;

import java.util.List;

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

    @Test
    public void canFilterLogs(){
        EventLog log = new EventLog();
        LogEntry logEntry = new LogEntry();
        logEntry.add(new SimpleLogElement("Hi"));

        log.add(logEntry);

        List<SimpleLogElement> filteredElements = log.filtered(By.type(SimpleLogElement.class));

        assertThat(filteredElements.size(), is(1));
    }
}
