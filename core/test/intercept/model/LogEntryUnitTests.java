package intercept.model;

import intercept.utils.Clock;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LogEntryUnitTests {
    @Test
    public void logEntriesAreComparedOnTime() {
        LogEntry one = new LogEntry(null);
        LogEntry two = new LogEntry(null);

        assertThat(one.compareTo(two), equalTo(0));
    }

    @Test
    public void logEntryEqualityConsidersElementContents() {
        LogEntry one = new LogEntry(null);
        LogEntry two = new LogEntry(new LogElement[]{new SimpleLogElement("foo")});

        assertThat(one.equals(two), equalTo(false));
    }

    @Test
    public void canLogLogElement() {
        LogEntry entry = new LogEntry();

        entry.addElement(new SimpleLogElement("Test Message"));

        List<LogElement> elements = entry.getElements();
        assertThat(elements.size(), is(1));
    }

    @Test
    public void addedLogElementsAreGivenEventTime() {
        LogEntry entry = new LogEntry();
        Clock.setTime(456);
        entry.addElement(new SimpleLogElement("Test Message"));

        List<LogElement> elements = entry.getElements();
        assertThat(elements.get(0).time(), equalTo(456L));
    }

    @Test
    public void earliestTimeIsTheTimeOfTheFirstEntry() {
        LogEntry logEntry = new LogEntry();
        Clock.setTime(123);
        logEntry.addElement(new SimpleLogElement("Test 1"));
        Clock.setTime(100);
        logEntry.addElement(new SimpleLogElement("Test 2"));

        assertThat(logEntry.earliestElementTime(), is(123L));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void hashCodeNotSupported() {
        LogEntry logEntry = new LogEntry();

        logEntry.hashCode();
    }
}
