

/**
 * An abstract class for uninformed search. It provides all methods
 * that is common to uninformed search algorithms, i.e. breath-first,
 * depth-first, depth-limited, and iterative deepening search
 * algorithms. It also specifies abstract methods that each uninformed
 * search algorithms must implement.
 * 
 * @author	Seung Chung
 * @modified	Tom Temple
 * @modified  	Sertac Karaman
 */
//import java.util.Deque; only on SE 6
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import static javafx.scene.input.KeyCode.V;

public abstract class UninformedSearch
{
    private State goal;
    
    /**
     * The search queue. It may be FIFO or LIFO depending on the type
     * of uninformed search.
     */
    protected LinkedList<Node> m_Q;
	
    /**
     * The number of extended partial paths.
     */
    protected int m_extendedPathsCount;
	
    /**
     * The maximum queue size reached during the search.
     */
    protected int m_maximumQueueSize;
	
    /**
     * Tag used for deciding whether visited list should be used or
     * not.
     */
    protected boolean m_useVisitedList;
	
    /**
     * Set of vertices visited during the search.
     */
    protected Set<State> m_visitedVertices;
	
    /**
     * Default constructor.
     */
    public UninformedSearch()
    {
	m_visitedVertices = new HashSet<State>(); 
	m_Q = new LinkedList<Node>();
	m_useVisitedList = true;
    }
	
    /**
     * This method is used to set for a visited list to be either used
     * or not used within the search, i.e. change the value of
     * <code>m_useVisitedList</code>.
     * 
     * @param useVistitedList	<code>true</code> if a visitedList
     * 							is to be used, otherwise
     * 							<code>false</code>.
     */
    public void setUseVisitedList(boolean useVistitedList)
    {
	m_useVisitedList = useVistitedList;
    }
	
    /**
     * This method is used to determine is the search is either set to
     * use or not use a visited list.
     * 
     * @return		<code>true</code> if a visitedList is to be used,
     * 				otherwise <code>false</code>.
     */
    public boolean isVisitedListUsed()
    {
	return m_useVisitedList;
    }
	
    /**
     * This method is used to determine if a partial path should be
     * extended or not. By default all partial paths are allowed to
     * be extended. But for an instance, in a depth-limited search,
     * a path should not be allowed to be extended if the depth of 
     * the path is greater than the specified maximum depth.
     * 
     * @param path		The path to be expanded.
     * @return			Returns <code>true</code> if the path should
     * 					be allowed to be extended, <code>false</code>
     * 					otherwise.
     */
    protected boolean isExtensionAllowed(Node node)
    {
	return true;
    }
	
    /**
     * Returns the number of paths that were extended during the
     * search.
     * 
     * @return			Number of paths extended.
     */
    public int getExtendedPathsCount()
    {
	return (m_extendedPathsCount);
    }
	
    /**
     * Returns the number of vertices that were visited during the search.
     * 
     * @return			Number of visited vertices.
     */
    public int getVisitedVerticesCount()
    {
	return (m_visitedVertices.size());
    }
	
    /**
     * Returns the maximum queue size reached during the search.
     * 
     * @return			Maximum queue size reached.
     */
    public int getMaximumQueueSize()
    {
	return m_maximumQueueSize;
    }
	
    /**
     * This method is used to add the set of extended paths to the
     * search queue. The location at which the set of extended paths is
     * added depends on the type of uninformed search.
     * 
     * @param extendedPaths		Set of extended paths to be added to
     * 							the search queue.
     */
    abstract protected void addToQueue(Set<Node> extendedPaths);
	
    /**
     * 1. Initialize Q with partial path (S) as only entry; set
     * 	  Visited = (S):
     * 
     * Initialize the search by reseting or clearing the member
     * variables. The search queue should be initialized to a
     * single partial path that only includes the start vertex and
     * the visited list should be initialized with the start vertex
     * as the only element.
     * 
     * @param S			Start state of the search.
     */
    public void initializeSearch(State S){
	//*** You fill in here! ***//
        m_visitedVertices = new HashSet<>();
        m_Q = new LinkedList<>();
        m_Q.add(new Node(S));
        m_visitedVertices.add(S);
        m_maximumQueueSize = 1;
    }
	
    /**
     * 4b. Find all children of head(N) not in Visited and create all
     *     the one-step extensions of N to each child:
     * 
     * Returns a set of search nodes that can be extended from the
     * current search node, N. Note that a node is the state is a leaf of
     * the search tree with a pointer to its parent.  To construct a
     * plan, we must traverse these pointers back to the start state at
     * the beginning of the tree.
     *
     * 
     * If the visited list is set to be used, the path should be extended with
     * only the children of head(N) that have not been visited. Note
     * that the visited list should be updated here, regardless of the
     * value of <code>m_useVisitedVertices</code>. 
     *
     * You may find it helpful to use hashCode() on a node to obtain a 
     * stable ordering over nodes.
     * 
     * @param Node		Node to be extended.
     * 
     * @return			Set of new reachable nodes.
     */
    protected Set<Node> getExtendedPaths(Node node)
    {
	// Set of new nodes reachable from node
	Set<Node> extendedPaths = new LinkedHashSet<Node>();
	//*** You fill in here! ***//
        for (Action action : node.getState().actionsAvailable()) {
            if (isVisitedListUsed() && m_visitedVertices.contains(action.getPost())) {
                continue;
            }
            extendedPaths.add(new Node(action.getPost(), node, action));
        }
        return extendedPaths;
    }
	
    /**
     * Performs a general uninformed graph search. During the search
     * <code>addToQueue(Set<Deque<Integer>> extendedPaths)</code>
     * method is used to add a set of paths to the search queue, and
     * before any path is extended,
     * <code>isExtensionnAllowed(Deque<Integer> path)</code> method is
     * used to check if a path is allowed to be extended or not.
     * This search method keeps track of the number of the extended
     * vertices, the number of the visited vertices, and the maximum
     * queue size reached during the search.
     * Make sure to keep track of visited vertices, regardless of the
     * value of <code>m_useVisitedVertices</code>. 
     * 
     * @param S			Index of the start vertex.
     * @param G			Index of the goal vertex.
     * @return			Returns a plan if it exists or
     *                             an empty List if one does not exist.
     *                             if we wish to search incrementally,
     * 				   a <code>null</code> is returned if niether 
     *                             the goal has been found nor has the search
     *                             been exhausted
     *
     *
     * Battlecode Addition:
     * I've split up the search into an initialization and an increment.
     * This will make it possible to unblock search process so that we
     * can use some of the turns while the search is thinking.
     **/
    public LinkedList<Action> search(State S,State G){
	LinkedList<Action> ret = startSearch(S,G);
	while (ret==null){
	    ret = incrementSearch(10000);
	}
	return ret;
    }

    /**
     * performs the initialization portion of the search.
     * all the parts that should not be repeated with incremental
     * computation
     **/
    public LinkedList<Action> startSearch(State S, State G)
    {
	//=============================================================
	//  Initialize Q with partial path (S) as only entry;
	//    set Visited = ().
	//=============================================================
	initializeSearch(S);
	goal = G;
	return incrementSearch(0);
    }

    /**
     * as described above, this function allows us to compute the plan
     * incrementally.  If the queue is not exhausted and we've expanded
     * countdown Nodes, we return with <code>null</code>.  
     * incrementSearch allows us to resume where we left off.
     *
     * @param int countdown  the number of Nodes to expand before terminating
     * @returns        the sequence of actions that represents the plan,
     *                 empty if infeasible, null if incomplete.
     **/
    public LinkedList<Action> incrementSearch(int countdown){		

	State G = goal;

	while (!m_Q.isEmpty() && countdown>0){
	    countdown--;
	    //=========================================================
	    //  Pop some partial path N from Q
	    //=========================================================
	    //*** You fill in here! ***//
        Node N = m_Q.pop();
        if (N.getState().equals(G)){
		return listify(N);
	    }
			
	    //=========================================================
	    //  Else, we're not done
	    //=========================================================
	    else{ 
		    
		// [Modification] If extension of path N is allowed,
		if (isExtensionAllowed(N)){
		   							
		    //=================================================
		    //     Find all children of N not in Visited
		    //     and create a search Node N
		    //     for each child. Then update the search
		    //     queue with the new leaves
		    //=================================================
		    //*** You fill in here! ***//
            setUseVisitedList(true);
            m_visitedVertices.add(N.getState());
            Set extendedPaths = getExtendedPaths(N);
            addToQueue(extendedPaths);
            m_extendedPathsCount += extendedPaths.size();
		   
					
		    // Update the number of maximum queue size reached
		    // during the search.
		    if (m_Q.size() > m_maximumQueueSize){
			m_maximumQueueSize = m_Q.size();
		    }
			
		}
	    }
	   
	}
	//=============================================================
	// fail or quit
	//=============================================================
	if (m_Q.isEmpty())
	    return new LinkedList<Action>();
	return null;
    }

    /**
     * Extract the sequence of actions from the search tree
     * @param Node n, a leaf in the search tree
     * @returns  a plan that would take you from the start state to Node n
     **/
    private LinkedList<Action> listify(Node n){
	LinkedList<Action> ret = new LinkedList<Action>();
	Node parent = n.getParent();
	while (parent!=null){
	    ret.add(n.getAction());
	    n = parent;
	    parent = n.getParent();
	}
	return ret;
    }
}
