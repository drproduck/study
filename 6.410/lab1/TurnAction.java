
import battlecode.common.*;

/**
 * TurnAction provides the ability to turn the robot to a new heading
 * @author Tom Temple
 **/
public final class TurnAction extends Action{
    RobotController rc;
    Direction d;
    public TurnAction(RobotState _pre,Direction _d, RobotController _rc){
	super(_pre, new RobotState(_pre.getRP(),_pre.getLocation(),_d),"turn "+_d);
	rc = _rc;
	d = _d;
    }

    public void doAction() throws GameActionException{
	rc.setDirection(d);
    }
}
