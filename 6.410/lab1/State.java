

/**
 * This is a class to encapsulate the state in a transition system.
 *
 * A state should be able to enumerate the available actions and their outcomes
 *
 * It is also important that a state implement "equals" and "hashCode"
 * correctly.
 *
 * A state is immutable.
 **/


import java.util.Set;

public abstract class State{
    /**
     * actionsAvailable is used to generate a set of Actions that can be
     * taken from a given state.  Actions are created here since this allows
     * them to be created with a referrence to the RobotController and 
     * thereby can implement the doAction() functor. For more, see the
     * definition of Action
     * @param RobotState state, the state from which we would like to know
     *        the available actions
     * @returns the available actions.
     **/
    public abstract Set<Action> actionsAvailable();

    public abstract boolean equals(Object other);
    public abstract int hashCode(); 
}