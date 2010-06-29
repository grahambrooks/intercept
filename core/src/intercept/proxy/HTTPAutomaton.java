package intercept.proxy;

class HTTPAutomaton {
    private State currentState;
    public HTTPAutomaton()
    {
        this.currentState = State.READING_HEADER;
    }
    public State currentState() {
        return currentState;
    }

    public enum State {
        READING_HEADER
    }
}
