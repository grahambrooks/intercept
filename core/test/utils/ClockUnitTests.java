package utils;

import intercept.utils.Clock;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ClockUnitTests {
    @Test
    public void canFixTheClockTime() {
        Clock.setTime(123L);

        assertThat(Clock.nanoTime(), equalTo(123L));
    }
}
