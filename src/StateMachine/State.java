package StateMachine;

import java.util.ArrayList;
import java.util.List;

public class State {
    private List<ConsumableTransition> consumableTransitions = new ArrayList<>();
    private List<EpsilonTransition> epsilonTransitions = new ArrayList<>();

    public void addTransition(EpsilonTransition epsilonTransition) {
        this.epsilonTransitions.add(epsilonTransition);
    }

    public void addTransition(ConsumableTransition consumableTransition) {
        this.consumableTransitions.add(consumableTransition);
    }

    public List<ConsumableTransition> getConsumableTransitions() {
        return this.consumableTransitions;
    }

    public List<EpsilonTransition> getEpsilonTransitions() {
        return this.epsilonTransitions;
    }
}
