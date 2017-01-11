/**
 * This class implements depth-first search by extending the
 * generalized uninformed search.
 * 
 * @author		Seung Chung
 */
//import java.util.Deque;

import java.util.Set;

public class DepthFirstSearch extends UninformedSearch
{
	/**
	 * This method is used to add the set of Nodes to the
	 * search queue. Depth-first search adds a set of nodes in the
	 * front of the search queue, i.e. the search queue is of LIFO
	 * type or a stack. Make sure to maintain the ordering of the
	 * nodes when added to the search queue. For example,
	 * if Q = (A,B,C,D) and the nodes = (E,F,G),
	 * then the result of adding the nodes to Q should
	 * be (E,F,G,A,B,C,D).
	 * 
	 * @param extendedPaths		Set of extended paths to be added to
	 * 			the search queue.
	 */
	protected void addToQueue(Set<Node> reachableNodes)
	{
	    //*** You fill in here ***//
		for (Node N : reachableNodes) {
			m_Q.addFirst(N);
		}
	}
}
s