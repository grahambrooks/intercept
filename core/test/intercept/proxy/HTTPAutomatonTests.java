package intercept.proxy;

import intercept.utils.Block;
import org.junit.Test;

import static intercept.proxy.HTTPAutomationEvent.BODY_DATA;
import static intercept.proxy.HTTPAutomationEvent.HEADER_DATA;
import static intercept.proxy.HTTPAutomationEvent.HEADER_END;
import static intercept.proxy.HTTPAutomatonState.HEADER_PENDING;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HTTPAutomatonTests {
    @Test
    public void automatonCreatedInHeaderPendingState() {
        HTTPAutomaton automaton = new HTTPAutomaton();

        assertThat(automaton.currentState(), is(HEADER_PENDING));
    }

    @Test
    public void automatonPassesHeaderDataToDelegate() {
        HTTPAutomaton automaton = new HTTPAutomaton();

        final byte[] received = new byte[5];
        final int[] count = new int[1];
        count[0] = 0;
        automaton.set(HEADER_DATA, new Block<Byte>() {
            @Override
            public void yield(Byte item) {
                received[count[0]] = item;
                count[0]++;
            }
        });

        byte[] data = "hello".getBytes();

        for (byte b : data) {
            automaton.process(b);
        }

        assertThat(received, is(data));
    }

    @Test
    public void automatonSignalsEndOfHeaderData() {
        HTTPAutomaton automaton = new HTTPAutomaton();

        final boolean[] received = new boolean[1];
        automaton.set(HEADER_END, new Block<Byte>() {
            @Override
            public void yield(Byte item) {
                received[0] = true;
            }
        });

        automaton.process((byte) '\n');
        automaton.process((byte) '\n');

        assertThat(received[0], is(true));
    }

    @Test
    public void automatonHandlesSimpleHeader() {
        byte[] message = "HTTP/1.0 GET /\n\n\n".getBytes();

        HTTPAutomaton automaton = new HTTPAutomaton();

        final boolean[] eof = new boolean[1];
        automaton.set(HEADER_END, new Block<Byte>() {
            @Override
            public void yield(Byte item) {
                eof[0] = true;
            }
        });

        for (byte b : message) {
            automaton.process(b);
        }
        assertThat(eof[0], is(true));
    }

    @Test
    public void automatonPassesDataToHandler() {
        byte[] message = "\n\ndata".getBytes();

        HTTPAutomaton automaton = new HTTPAutomaton();

        final byte[] received = new byte[4];
        final int[] count = new int[1];
        count[0] = 0;

        automaton.set(BODY_DATA, new Block<Byte>() {
            @Override
            public void yield(Byte item) {
                received[count[0]] = item;
                count[0]++;
            }
        });

        for (byte b : message) {
            automaton.process(b);
        }
        assertThat(received, is("data".getBytes()));

    }
}
