package intercept.proxy;

import intercept.utils.Block;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HTTPAutomatonTests {
    @Test
    public void automatonCreatedInReadingHeaderState() {
        HTTPAutomaton automaton = new HTTPAutomaton();

        assertThat(automaton.currentState(), is(HTTPAutomaton.State.READING_HEADER));
    }

    @Test
    public void automatonPassesHeaderDataToDelegate() {
        HTTPAutomaton automaton = new HTTPAutomaton();

        final byte[][] received = new byte[1][1];
        automaton.setDelegate(HTTPAutomaton.State.READING_HEADER, new Block<byte[]>() {
            @Override
            public void yield(byte[] item) {
                received[0] = item;
            }
        });

        byte[] data = "hello".getBytes();

        automaton.process(data);

        assertThat(received[0], is(data));
    }

    @Test
    public void automatonSignalsEndOfHeaderData() {
        HTTPAutomaton automaton = new HTTPAutomaton();

        final boolean[] received = new boolean[1];
        automaton.setDelegate(HTTPAutomaton.State.HEADER_RECEIVED, new Block<byte[]>() {
            @Override
            public void yield(byte[] item) {
                received[0] = true;
            }
        });

        byte[] data = "\n\n".getBytes();

        automaton.process(data);

        assertThat(received[0], is(true));
    }

}
