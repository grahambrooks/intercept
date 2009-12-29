package intercept.logging;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;


public class EventLoggerUnitTests {
    @Test
    public void canConstructLogger() {
        new EventLogger(new EventLog());
    }

    @Test
    public void constructedLoggerIsEmpty() {
        EventLog log = new EventLog();
        assertThat(log.getEntries().size(), is(0));
    }

    @Test
    public void canAddAnEntry() {
        EventLog log = new EventLog();
        EventLogger e = new EventLogger(log);
        e.log(EventLogger.e("Some message"));
        assertThat(log.getEntries().size(), is(1));
        assertThat(log.getEntries().get(0).elements.get(0).getMessage(), is("Some message"));
    }
}
