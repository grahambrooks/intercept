package intercept.proxy;

class HTTPAutomaton extends Automaton<HTTPAutomaton.State, Byte, HTTPAutomaton.Event> {
    public enum State {
        HEADER_PENDING,
        READING_HEADER,
        EOH_PENDING, BODY_PENDING, READING_BODY,
    }

    public enum Event {
        HEADER_DATA,
        HEADER_EOL,
        HEADER_END,
        BODY_DATA,
    }

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
        super(State.HEADER_PENDING);

        addTransition(State.HEADER_PENDING, characterMatcher, State.READING_HEADER, Event.HEADER_DATA);
        addTransition(State.READING_HEADER, characterMatcher, State.READING_HEADER, Event.HEADER_DATA);
        addTransition(State.READING_HEADER, eolMatcher, State.HEADER_PENDING, Event.HEADER_EOL);

        addTransition(State.HEADER_PENDING, eolMatcher, State.EOH_PENDING, null);
        addTransition(State.EOH_PENDING, eolMatcher, State.BODY_PENDING, Event.HEADER_END);

        addTransition(State.BODY_PENDING, bodyMatcher, State.READING_BODY, Event.BODY_DATA);
        addTransition(State.READING_BODY, bodyMatcher, State.READING_BODY, Event.BODY_DATA);
    }
}
