package StateMachine;

public class EpsilonTransition extends Transition {

    public EpsilonTransition(State nextState) {
        this.nextState = nextState;
    }

    public State getNextState() {
        return nextState;
    }
}
