package StateMachine;

public class ConsumableTransition extends Transition {
    private final char minCharValue;
    private final char maxCharValue;

    public ConsumableTransition(State nextState, char minCharValue, char maxCharValue) {
        this.nextState = nextState;
        this.minCharValue = minCharValue;
        this.maxCharValue = maxCharValue;
    }

    public ConsumableTransition(ConsumableTransition consumableTransition) {
        this.nextState = consumableTransition.nextState;
        this.minCharValue = consumableTransition.minCharValue;
        this.maxCharValue = consumableTransition.maxCharValue;
    }

    public State getNextState(char symbol) {

        if (inRange(minCharValue, symbol, maxCharValue))
            return nextState;

        return null;
    }

    private static boolean inRange(int min, int x, int max) {
        return min <= x && x <= max;
    }
}