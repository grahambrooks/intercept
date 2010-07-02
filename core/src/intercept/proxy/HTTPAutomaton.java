package intercept.proxy;

import static intercept.proxy.HTTPAutomationEvent.BODY_DATA;
import static intercept.proxy.HTTPAutomationEvent.HEADER_DATA;
import static intercept.proxy.HTTPAutomationEvent.HEADER_END;
import static intercept.proxy.HTTPAutomationEvent.HEADER_EOL;
import static intercept.proxy.HTTPAutomatonState.BODY_PENDING;
import static intercept.proxy.HTTPAutomatonState.EOH_PENDING;
import static intercept.proxy.HTTPAutomatonState.HEADER_PENDING;
import static intercept.proxy.HTTPAutomatonState.READING_BODY;
import static intercept.proxy.HTTPAutomatonState.READING_HEADER;

class HTTPAutomaton extends Automaton<HTTPAutomatonState, Byte, HTTPAutomationEvent> {

    DataMatcher<Byte> characterMatcher = new DataMatcher<Byte>() {
        @Override
        public boolean matches(Byte data) {
            return Character.isLetter(data) || Character.isDigit(data);
        }
    };
    DataMatcher<Byte> eolMatcher = new DataMatcher<Byte>() {
        @Override
        public boolean matches(Byte data) {
            return data.equals((byte) '\n');
        }
    };
    DataMatcher<Byte> bodyMatcher = new DataMatcher<Byte>() {
        @Override
        public boolean matches(Byte data) {
            return true;
        }
    };

    public HTTPAutomaton() {
        super(HEADER_PENDING);

        addTransition(HEADER_PENDING, characterMatcher, READING_HEADER, HEADER_DATA);
        addTransition(READING_HEADER, characterMatcher, READING_HEADER, HEADER_DATA);
        addTransition(READING_HEADER, eolMatcher, HEADER_PENDING, HEADER_EOL);

        addTransition(HEADER_PENDING, eolMatcher, EOH_PENDING, null);
        addTransition(EOH_PENDING, eolMatcher, BODY_PENDING, HEADER_END);

        addTransition(BODY_PENDING, bodyMatcher, READING_BODY, BODY_DATA);
        addTransition(READING_BODY, bodyMatcher, READING_BODY, BODY_DATA);
    }
}
