package team16410;

/**
 * This class must conform to the regulations imposed by the Battlecode
 * rules.  For instance, we are not allowed to open the map file and so
 * the relevant information is repeated here.
 *
 * This requires that if you wish to modify the map, you must change
 * the map here as well as the xml map file.  If you want to move the 
 * goal location however, you need only change the map here.
 *
 * This class provides the actionsAvailable() method which is responsible
 * for enumerating the possible transitions.
 *  @author Tom Temple
 **/

import battlecode.common.*;
import static battlecode.common.GameConstants.*;

//import java.util.Deque; //not available in Java 1.5
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;
import java.io.*;

public class RobotPlayer implements Runnable {

   private final RobotController myRC;

    //tells whether this robot is allowed to move
    private boolean active = false;
    private boolean map_complete = false;
    private MapLocation loc;
    private Direction dir;
    private RobotState goal;
    private RobotState start;
    private boolean[][] map;
    private MapLocation topright;
    private MapLocation bottomleft;
    private LinkedList<Action> actions;
    private UninformedSearch searcher;

   public RobotPlayer(RobotController rc) {
       searcher = new BreadthFirstSearch();
       //searcher = new DepthFirstSearch();
       //searcher = new IterativeDeepeningSearch();

       myRC = rc;
     	BufferedReader reader = null;
	// The following ascii-art is the map that will be used by the
	// search algorithm.  If this is going to work, it must match the
	// xml file used by Battlecode.
	// the goal 'G' may be moved without modifying the xml
	// any other changes must be mirrored.
	String wholemap= "                ##############################\n		#A                           #\n		                                #  ##                    #   #\n		                                # #####                 ###  #\n                                       		# ######      ##       ####  #\n	                                 	#  ##       ######   ######  #\n	                                 	#      #    ######   ######  #\n	                                  	#     ##    ######   ####    #\n	                                 	#     ###    #####    ##     #\n	                                	#      ##     ####           #\n	                                       	#  ##  ###    #####          #\n	                                	# ###   ###    ####          #\n	                                       	#  ###      #######          #\n	                                    	#  ####     ######           #\n	                                	#            #####           #\n	                                  	#              ###           #\n	                                  	#               ###          #\n	                                	#               ###    #     #\n	                                   	#               ##   #####   #\n	                                 	#              ###   ######  #\n                                 		#             ####   ######  #\n                                  		#            ####    ### ##  #\n                                   		#           ####     ### ##  #\n                                  		#           ### G   ######## #\n                                		#           ###     ######## #\n                                		#           ###   ##### #### #\n                                		#           ###  ######  ##  #\n                                		#                 ###        #\n                                		#                           B#\n                                		##############################";
	String[] lines = wholemap.split("\n");
	int height = 30;
	int width = 30;
	map = new boolean[height][width];
	for (int i=0;i<height;i++){
	    String line = lines[i].trim();
	    //System.out.println("line="+line);
	    for (int j=0;j<width;j++){
		    switch (line.charAt(j)){
		    case ' ': map[i][j] = true; break;
		    case '#': map[i][j] = false; break;
		    case 'G': 
			map[i][j] = true;
			goal = new RobotState(this,new MapLocation(j,i),Direction.SOUTH);
			break;
		    case 'A': 
			map[i][j] = true;
			start = new RobotState(this,new MapLocation(j,i),Direction.NORTH);
			break;
		    default:
			map[i][j] = false; break;
		    
		}
	    }
	}
	//actions = null;
        //actions = searcher.startSearch(start,goal);
   }

   public void run(){
       while(true){
	   try{
	       while(myRC.isMovementActive()) {
		 myRC.yield();
	       }
	       if (actions!=null){
		   if (actions.isEmpty()){
		       while(true){
			   myRC.yield();
		       }
		   }
		   Action a = actions.removeLast();
		   System.out.println("doing action "+a);
		   a.doAction();
		   myRC.yield();
	       }else{
		   //actions = searcher.incrementSearch(10000);
		   actions = searcher.search(start,goal);
		   //Problem 2
		   //System.out.println...
		   myRC.yield();
	       }
	     /*** end of main loop ***/
	   
	   }catch(Exception e) {
	     System.out.println("caught exception: "+e);
	     e.printStackTrace();
	      while(true) {
		 myRC.yield();
	       }
	   }
       
      }
   }

    /**
     * actionsAvailable is used to generate a set of Actions that can be
     * taken from a given state.  Actions are created here since this allows
     * them to be created with a referrence to the RobotController and 
     * thereby can implement the doAction() functor. For more, see the
     * definition of Action
     * @param RobotState state, the state from which we would like to know
     *        the available actions
     * @returns the available actions.
     *
     * As a practical matter, I am not including directions like SOUTH_EAST
     * or the backward() action at the moment since the search is slow enough
     * as it is.
     **/
    public Set<Action> actionsAvailable(RobotState state){
	Set<Action> actions = new HashSet<Action>();
	for(Direction d : Direction.values())
	    if (!d.equals(state.getDirection()))
		if (d.equals(Direction.SOUTH) ||
		    d.equals(Direction.EAST) ||
		    d.equals(Direction.NORTH) ||
		    d.equals(Direction.WEST))
		actions.add(new TurnAction(state,d,myRC));
	if (canMove(state.getLocation().add(state.getDirection())))
	    actions.add(new ForwardAction(state,myRC));
	//if (canMove(state.getLocation().subtract(state.getDirection())))
	//    actions.add(new BackwardAction(state,myRC));
	return actions;
    }

    /**
     * a helper method to actionsAvailable() that queries the map
     * and decides if move actions are legal.
     * Note that since we aren't _actually_ at location _loc (nor have
     * we ever seen it), we cannot
     * use the RobotController methods that do the same thing.
     */
    private boolean canMove(MapLocation _loc){
	int x = _loc.getX();
	int y = _loc.getY();
	//System.out.println("map["+x+"]["+y+"]="+map[y][x]);
	if (x<0 || y<0 ||x>=map[0].length || y>=map.length)
	    return false;
	return (map[y][x]);
    }
}
