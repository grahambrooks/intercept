package intercept.proxy;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AutomatonTests {
    enum TestStates {
        INITIAL,
        STATE1,
    }
    enum TestEvents {
        EVENT1
    }

    class TestAutomaton extends Automaton<TestStates, Byte, TestEvents>{
        public TestAutomaton(TestStates initialState) {
            super(initialState);
        }
    }

    DataMatcher<Byte> trueMatcher = new DataMatcher<Byte>() {
        @Override
        public boolean matches(Byte data) {
            return true;
        }
    };

    @Test
    public void mustSpecifyInitialState() {
        new Automaton<TestStates, Byte, TestEvents>(TestStates.INITIAL);
    }

    @Test
    public void canAddTranition() {
        TestAutomaton automaton = new TestAutomaton(TestStates.INITIAL);
        automaton.addTransition(TestStates.INITIAL, null, TestStates.INITIAL, TestEvents.EVENT1);
    }

    @Test
    public void positiveMatchesChangeState() {
        TestAutomaton automaton = new TestAutomaton(TestStates.INITIAL);
        automaton.addTransition(TestStates.INITIAL, trueMatcher, TestStates.STATE1, TestEvents.EVENT1);

        automaton.process(new Byte("10"));

        assertThat(automaton.currentState(), is(TestStates.STATE1));
    }
}
