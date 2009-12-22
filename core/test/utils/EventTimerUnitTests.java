package utils;

import intercept.utils.EventTimer;
import static intercept.utils.PatternMatcher.matches;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class EventTimerUnitTests {
    @Test
    public void eventTimerStartsOnConstruction() {
        EventTimer timer = new EventTimer();
        assertThat(timer.startTime() != 0, is(true));
    }

    @Test
    public void eventTimerMeasuresElapsedTime() throws Exception {
        EventTimer timer = new EventTimer();
        Thread.sleep(1);
        assertThat(timer.elapsed() != 0, is(true));
    }

    @Test
    public void eventTimerToString() {
        EventTimer timer = new EventTimer();
        assertThat(timer.toString(), matches("[0-9]+ ms"));
    }
}
