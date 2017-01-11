
import battlecode.common.*;

/**
 * this action knows how to move backward
 **/

public final class BackwardAction extends Action{
    RobotController rc;
    BackwardAction(RobotState _pre,RobotController _rc){
	super(_pre, new RobotState(_pre.getRP(),_pre.getLocation().subtract(_pre.getDirection()),_pre.getDirection()),
	      "move backward");
	rc = _rc;
    }
    public void doAction() throws GameActionException{
	rc.moveBackward();
    }
}