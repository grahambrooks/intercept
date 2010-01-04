package intercept.proxy;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StreamControlUnitTests {
    @Test
    public void dataNotExpectedIfNotWaitingForDisconnectAndZeroExpectedBytes() {
        StreamControl streamControl = new StreamControl(0, false);

        assertThat(streamControl.dataExpected(), is(false));
        assertThat(streamControl.expected(), is(0));
        assertThat(streamControl.waitForDisconnect(), is(false));
    }

    @Test
    public void dataExpectedIfWaitingForDisconnectAndZeroExpectedBytes() {
        StreamControl streamControl = new StreamControl(0, true);

        assertThat(streamControl.dataExpected(), is(true));
        assertThat(streamControl.expected(), is(0));
        assertThat(streamControl.waitForDisconnect(), is(true));
    }

    @Test
    public void oneByteExpectedIfNotWaitingForDisconnectAndOneExpectedBytes() {
        StreamControl streamControl = new StreamControl(1, false);

        assertThat(streamControl.dataExpected(), is(true));
        assertThat(streamControl.moreDataExpected(0), is(true));
        assertThat(streamControl.expected(), is(1));
        assertThat(streamControl.waitForDisconnect(), is(false));

        assertThat(streamControl.moreDataExpected(1), is(false));
        assertThat(streamControl.dataExpected(), is(true));
    }

    @Test
    public void streamControlRendersMeaningfulText() {
        StreamControl streamControl = new StreamControl(100, false);

        assertThat(streamControl.toString(), is("Stream control expected 100 WaitForDisconnect is false"));
    }
}
