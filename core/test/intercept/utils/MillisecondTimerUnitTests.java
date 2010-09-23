package intercept.utils;

import org.junit.Test;

import static intercept.utils.PatternMatcher.matches;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MillisecondTimerUnitTests {
    @Test
    public void eventTimerStartsOnConstruction() {
        MillisecondTimer timer = new MillisecondTimer();
        assertThat(timer.startTime() != 0, is(true));
    }

    @Test
    public void eventTimerMeasuresElapsedTime() throws Exception {
        EventTimer timer = new MillisecondTimer();
        Thread.sleep(1);
        assertThat(timer.elapsed() != 0, is(true));
    }

    @Test
    public void eventTimerToString() {
        EventTimer timer = new MillisecondTimer();
        assertThat(timer.toString(), matches("[0-9]+ ms"));
    }
}
