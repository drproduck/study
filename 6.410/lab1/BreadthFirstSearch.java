/**
 * This class implements breadth-first search by extending the
 * generalized uninformed search.
 * 
 * @author		Seung Chung
 */
//import java.util.Deque;
import java.util.Set;

public class BreadthFirstSearch extends UninformedSearch
{	/**
	 * This method is used to add the set of nodes to the
	 * search queue. Breadth-first search adds the set at
	 * the back of the search queue, i.e. the search queue is of
	 * FIFO type. Make sure to maintain the ordering
	 *  when added to the search queue. For example,
	 * if Q = (A,B,C,D) and the nodes = (E,F,G),
	 * then the result of adding the nodes to Q should
	 * be (A,B,C,D,E,F,G).
	 * 
	 * @param reachableNodes	Set of nodes to be added to
	 * 				the search queue.
	 **/
	protected void addToQueue(Set<Node> reachableNodes)
	{
		//*** You fill in here! ***//
		for (Node N : reachableNodes) {
			m_Q.addLast(N);
		}
	}
}
