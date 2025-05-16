import StateMachine.*;

public class Regex {

    StateMachine sm;

    public Regex(String reg) {
        this.sm = compile(reg);
    }

    public static boolean match(StateMachine sm, String s) {
        return matchWithStartState(sm.getInitialState(), sm.getLastState(), s, 0);
    }

    private static boolean matchWithStartState(State initial, State end, String s, int curr) {

        if (initial == end && curr >= s.length()) {
            return true;
        }

        for (EpsilonTransition t : initial.getEpsilonTransitions()) {
            if (matchWithStartState(t.getNextState(), end, s, curr)) {
                return true;
            }
        }

        if (curr >= s.length()) {
            return false;
        }

        for (ConsumableTransition t : initial.getConsumableTransitions()) {
            State nextState = t.getNextState(s.charAt(curr));
            if (nextState != null && matchWithStartState(nextState, end, s, curr + 1)) {
                return true;
            }
        }

        return false;
    }

    private static StateMachine compile(String reg) {

        return null;
    }
}
