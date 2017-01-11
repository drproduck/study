import battlecode.common.*;

/**
 * this action knows how to move forward
 * When are you going to catch up with the times, Java?
 **/

public final class ForwardAction extends Action{
    RobotController rc;
    ForwardAction(RobotState _pre,RobotController _rc){
	super(_pre, new RobotState(_pre.getRP(),_pre.getLocation().add(_pre.getDirection()),_pre.getDirection()),
	      "move forward");
	rc = _rc;
    }

    public void doAction() throws GameActionException{
	rc.moveForward();
    }
}
	      