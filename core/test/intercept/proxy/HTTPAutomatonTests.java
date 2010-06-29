package intercept.proxy;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HTTPAutomatonTests {
    @Test
    public void automatonCreatedInReadingHeaderState() {
        HTTPAutomaton automaton = new HTTPAutomaton();

        assertThat(automaton.currentState(), is(HTTPAutomaton.State.READING_HEADER));
    }

}
