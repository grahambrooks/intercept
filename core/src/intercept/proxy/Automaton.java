package intercept.proxy;

import intercept.utils.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Automaton<S, D, E> {
    private S currentState;

    private class Transition {
        DataMatcher<D> matcher;
        S targetState;
        private E event;

        public Transition(DataMatcher<D> matcher, S targetState, E event) {
            this.matcher = matcher;
            this.targetState = targetState;
            this.event = event;
        }
    }

    private Map<S, List<Transition>> transitions;
    private Map<E, Block<D>> eventHandlers;


    public Automaton(S initialState) {
        this.currentState = initialState;
        this.transitions = new HashMap<S, List<Transition>>();
        this.eventHandlers = new HashMap<E, Block<D>>();
    }

    public void addTransition(S initialState, DataMatcher<D> matcher, S targetState, E event) {
        if (transitions.get(initialState) == null) {
            transitions.put(initialState, new ArrayList<Transition>());
        }

        transitions.get(initialState).add(new Transition(matcher, targetState, event));
    }

    public void process(D data) {
        List<Transition> options = transitions.get(currentState);
        if (options != null) {
            for (Transition option : options) {
                if (option.matcher.matches(data)) {
                    handleEvent(option, data);
                    currentState = option.targetState;
                }
            }
        }
    }

    public S currentState() {
        return currentState;
    }

    public void set(E event, Block<D> handler){
        eventHandlers.put(event, handler);
    }


    private void handleEvent(Transition transition, D data) {
        Block<D> handler = eventHandlers.get(transition.event);
        if (handler != null) {
            handler.yield(data);
        }
    }
}
