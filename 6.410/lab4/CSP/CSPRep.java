

/**
 * Title:        CSPRep
 * Description:  An abstract class, denoting a finite domain
 *               constraint satisfaction problem.  Define a 
 *               subclass to represent a specific kind of CSP.
 * Copyright:    Copyright (c) 2005-2007
 * Company:      MIT
 * @author Andreas Hofmann and Brian Williams
 * @version 2.0
 */

import java.util.List;

/*
The class CSPRep specifies a CSP, where a CSP is represented by
a set of variables, a finite domain, denoting the domain of values, 
shared by every variable, and a set of constraints.  Constraints are
restricted to binary constraints in the implementation.  The CSPRep
class includes the elements variables, domain, and constraints.  

CSPRep also includes constructor, initialize, and print methods,
which must be overridden in a class that inherits from CSPRep.

Finally, the class has methods backtrack, and backtrack_fc.  
You will implement the method backtrack in problem 8.1, and the 
method backtrack_fc in problem 8.2.  Note that these methods are 
to be implemented at the level of the CSP abstraction, in terms 
of the classes CSPRep, CSPVariable, CSPDomain, and
CSPConstraint.  The implementation of these methods should not
contain code specific to a particular type of CSP, such as N-queens.
*/

public class CSPRep
{
	/**
	 * A finite list of the variables of the CSP.
	 */
    List<CSPVariable> variables;
    
	/**
	 * A finite domain of values that all variables of the CSP range over.
	 */
    CSPDomain domain;

    /**
	 * A finite list of the binary constraints of the CSP.  
	 * Each constraint ranges over a pair of variables in VARIABLES. 
	 */
	 List<CSPConstraint> constraints;


	 /**
	  * A boolean, run-time flag.  If true, then debugging 
	  * information is printed during execution..
	  * As you solve this problem set, you may want to exploit
	  * this flag to print out your own debugging information
	  */
	 boolean debug_print = false;

	 /**
	  * Default constructor.  Does nothing.
	  */
	 public CSPRep()
    {

    }

	 
	 /**
	  * Default initialization routine.  (Re)initializes
	  * the CSP, in preparation for backtrack search.
	  * This method does nothing.
	  */
    public void initialize()
    {

    }

	 /**
	  * Default print routine.  Typically prints out the
	  * domain elements for each variable that has not (yet)
	  * been pruned.
	  * The default method does nothing.
	  */
    public void print()
    {
		for (CSPVariable v : variables) {

		}
	}


	 /**
	  * Back track search method.  Searches chronologically through
	  * assignments for each CSP variable to an element
	  * of its domain.  
	  * 
	  * Returns <code> True </code>if an assignment 
	  * is found that is consistent with all of the CSP's constraints.
	  * Returns <code> False </code> if all assignments have been exhausted 
	  * and no consistent assignment has found.
	  * 
	  * @return		<code>True</code> iff CSP is consistent.
	  */
    public boolean backtrack()
    {
		int i = 0;
		variables.get(i).copyDomainValuesTo(0);
		variables.get(i).makeDomainValuesIteratorFrom(0);
		for (;;) {
			if (variables.get(i).select() == null) {
				variables.get(i).restoreDomainValuesFrom(0);
				i--;
				if (i < 0) {
					return false;
				}
			} else {
				if (i == variables.size()-1) {
					return true;
				}
				i++;
				variables.get(i).copyDomainValuesTo(0);
				variables.get(i).makeDomainValuesIteratorFrom(0);
			}
		}

    }

	 /**
	  * Back track search with Forward Checking method (BT-FC).  
	  * Searches chronologically through assignments for each 
	  * CSP variable to elements of its UNPRUNED domain values.
	  * 
	  * Returns <code> True </code>if an assignment 
	  * is found that is consistent with all of the CSP's 
	  * constraints.  Returns <code> False </code> if all 
	  * assignments have been exhausted and no consistent assignment 
	  * has been found.
	  * 
	  * BackTrack with Forward Checking (BT-FC) is distinguished from
	  * BackTracking (BT) in that, after each assignment to variable v, 
	  * directed arc-consistency is applied to the constraints 
	  * involving V, in order to prune domain elements of any
	  * unassigned variable that is inconsistent with V's assignment.
	  * 
	  * @return		<code>True</code> iff CSP is consistent.
	  */
    public boolean backtrack_fc()
    {
		int i = 0;
		for (CSPVariable var :
				variables) {
			var.copyDomainValuesTo(i);
		}
		variables.get(i).makeDomainValuesIteratorFrom(i);
		while (i > -1 && i < variables.size()) {
			if (variables.get(i).selectFC(i) == null) {
				i--;
				if (debug_print)
				System.out.println("stepping backward: i = "+ i);
				if (i > -1) {
					for (int j = i; j < variables.size(); j++) {
						variables.get(j).restoreDomainValuesFrom(i);
					}
					//for (CSPVariable var :
					//		variables) {
					//	var.copyDomainValuesTo(i);
					//}
				}
			} else {
				i++;
				if (debug_print)
				System.out.println("stepping forward: i = "+ i);
				if (i < variables.size()) {
					for (int j = i; j < variables.size(); j++) {
						variables.get(j).copyDomainValuesTo(i);
					}
					//for (CSPVariable var :
					//		variables) {
					//	var.copyDomainValuesTo(i);
					//}
					variables.get(i).makeDomainValuesIteratorFrom(i);
				}
			}
		}
		if (i < 0) {
			return false;
		}
		return true;
    }


	/**save initial domains to cache via copyDomainValues
	 *
	 */

}
