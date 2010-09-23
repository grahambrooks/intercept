package intercept.logging;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class EventLoggerUnitTests {
    @Test
    public void canConstructLogger() {
        new DefaultEventLogger(new EventLog());
    }

    @Test
    public void constructedLoggerIsEmpty() {
        EventLog log = new EventLog();
        assertThat(log.getEntries().size(), is(0));
    }

    @Test
    public void canAddAnEntry() {
        EventLog log = new EventLog();
        DefaultEventLogger e = new DefaultEventLogger(log);

        e.logError("Some message");

        assertThat(log.getEntries().size(), is(1));
        assertThat(log.getEntries().get(0).elements.get(1).getMessage(), is("Some message"));
    }
}
