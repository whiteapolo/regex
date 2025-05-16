package StateMachine;

import java.util.LinkedList;
import java.util.List;

public class StateMachine {
    LinkedList<State> states;

    public StateMachine(String s) {

        this.states = new LinkedList<>();
        State initial = addState();

        for (int i = 0; i < s.length(); i++) {
            State end = addState();
            initial.addTransition(new ConsumableTransition(end, s.charAt(i), s.charAt(i)));
            initial = end;
        }
    }

    public StateMachine(char symbol) {
        State initial = pushState();
        State end = addState();

        initial.addTransition(new ConsumableTransition(end, symbol, symbol));
    }

    public State addState() {
        State state = new State();
        states.addLast(state);
        return state;
    }

    public State pushState() {
        State state = new State();
        states.addFirst(state);
        return state;
    }

    public State getInitialState() {
        return states.getFirst();
    }

    public State getLastState() {
        return states.getLast();
    }

    public void concatWithTransition(StateMachine sm) {
        this.states.getLast().addTransition(new EpsilonTransition(sm.getInitialState()));
        this.states.addAll(sm.states);
    }

    public void union(StateMachine sm) {
        State firstInitial = this.getInitialState();
        State firstLast = this.getLastState();
        State secondInitial = sm.getInitialState();
        State secondLast = sm.getLastState();

        this.states.addAll(sm.states);
        State initial = this.pushState();
        State last = this.addState();

        initial.addTransition(new EpsilonTransition(firstInitial));
        initial.addTransition(new EpsilonTransition(secondInitial));
        firstLast.addTransition(new EpsilonTransition(last));
        secondLast.addTransition(new EpsilonTransition(last));
    }

    public void oneOrMore() {
        // add transition from last to initial
        this.getLastState().addTransition(new EpsilonTransition(this.getInitialState()));
    }

    public void zeroOrMore() {
        // add transition from initial to last and from last to initial
        this.getLastState().addTransition(new EpsilonTransition(this.getInitialState()));
        this.getInitialState().addTransition(new EpsilonTransition(addState()));
    }

    public void optional() {
        // add transition from initial to last
        this.getInitialState().addTransition(new EpsilonTransition(addState()));
    }
}