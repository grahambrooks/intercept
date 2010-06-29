package intercept.proxy;

import intercept.utils.Block;

import java.util.HashMap;
import java.util.Map;

class HTTPAutomaton {
    private State currentState;
    private Map<State, Block<byte[]>> delegates;

    public HTTPAutomaton() {
        this.currentState = State.READING_HEADER;
        delegates = new HashMap<State, Block<byte[]>>();
    }

    public State currentState() {
        return currentState;
    }

    public void process(byte[] data) {
        if (data[0] == '\n' && data[1] == '\n') {
            delegates.get(State.HEADER_RECEIVED).yield(data);
        } else {
            delegates.get(currentState).yield(data);
        }
    }

    public void setDelegate(State state, Block<byte[]> block) {
        delegates.put(state, block);
    }

    public enum State {
        HEADER_RECEIVED, READING_HEADER
    }
}
