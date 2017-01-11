/**
 * A node encapsulates a vertex in the search tree.  To maintain the tree
 * structure, each node maintains a pointer to its parent.
 * At each node we store the action required to enter it (from the parent)
 * as well as the distance from the root (for convenience in implementing
 * depth-limited search).
 * @author Tom Temple
 **/
public class Node{

    private final State state;
    private final Node parent;
    private final Action action;
    private final int length;

    public State getState(){	return state;    }
    public Node getParent(){    return parent;    }
    public Action getAction(){  return action; }
    public int size(){  return length; }

    public Node(State s){
	state = s;
	parent = null;
	action = null;
	length = 0;
    }

    public Node(State s,Node n,Action a){
	state = s;
	parent = n;
	action = a;
	length = n.size()+1;
    }

    public String toString(){
	if (parent==null)
	    return state.toString();
	return parent.toString() +" then\n" +action.toString();
    }
}
