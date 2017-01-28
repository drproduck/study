

import java.util.Set;
import battlecode.common.*;


/**
 * A robot state encapsulates our definition of a "state" for the current
 * problem.  In our case this only refers to position and orientation.
 * We also store a reference to the RobotPlayer.  This is because it is
 * convenient to as a state what actions it has available.  
 * We could just as easily store the RobotPlayer in the search function
 * to accomplish the same thing.  
 **/
public final class RobotState extends State{

    private final RobotPlayer myRP;
    private final MapLocation location;
    private final Direction direction;

    public Direction getDirection(){	return direction;    }
    public MapLocation getLocation(){	return location;    }
    public RobotPlayer getRP(){	return myRP;    }

    public RobotState(RobotPlayer rp, MapLocation loc, Direction dir){
	myRP = rp;
	location = loc;
	direction = dir;
    }

    /**
     *  Return all of the actions that can be taken from the current state
     **/
    public Set<Action> actionsAvailable(){
	return myRP.actionsAvailable(this);
    }

    public int hashCode(){
	return(location.hashCode()*direction.hashCode());
    }

    /**
     * Two states are equal iff their locations and directions are equal
     **/
    public boolean equals(Object other){
	if (!(other instanceof RobotState))
	    return false;
	if (location.equals(((RobotState)other).getLocation()) &&
	    direction.equals(((RobotState)other).getDirection()))
	    return true;
	return false;
    }

    public String toString(){
	return "in "+location+" facing "+direction;
    }

  					      
}
	
		