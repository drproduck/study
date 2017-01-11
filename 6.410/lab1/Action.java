

/**
 *  An Action is a labeled transition between two States.
 *  Since we do not require the set of actions to be fixed, the actions
 *  available in different states will be given different labels. 

 *  The Actions are expected to know how to execute themselves.  If Java had
 *  first-class functions, this would be pretty easy.  We'll proceed by
 *  making Action abstract and requiring subclasses to know how to do it.

 *  Actions are immutable.  This is important because collection uses shallow
 *  copy.  We could get around this using serialization though.
 * @author Tom Temple
 **/
import battlecode.common.*;

public abstract class Action{
    
    private final State pre;
    private final State post;
    private final String label;
    public State getPre(){ return pre;}
    public State getPost(){ return post;}
    public String label(){ return label;}

    /**
     * doAction is the functor here.  The point is that if I have a list
     * of actions, this points to code that will execute them.
     **/
    public abstract void doAction() throws GameActionException;   

    public Action(State _pre, State _post, String _label){
	pre = _pre;
	post = _post;
	label = _label;
    }

    public String toString(){
	return label.toString() + " from "+pre+" to "+post;
    }
}
