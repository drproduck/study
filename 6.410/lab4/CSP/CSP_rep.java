

/**
 * Title:        CSP_rep
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
The class CSP_rep specifies a CSP, where a CSP is represented by 
a set of variables, a finite domain, denoting the domain of values, 
shared by every variable, and a set of constraints.  Constraints are
restricted to binary constraints in the implementation.  The CSP_rep 
class includes the elements variables, domain, and constraints.  

CSP_rep also includes constructor, initialize, and print methods, 
which must be overridden in a class that inherits from CSP_rep.  

Finally, the class has methods backtrack, and backtrack_fc.  
You will implement the method backtrack in problem 8.1, and the 
method backtrack_fc in problem 8.2.  Note that these methods are 
to be implemented at the level of the CSP abstraction, in terms 
of the classes CSP_rep, CSP_Variable, CSP_Domain, and 
CSP_Constraint.  The implementation of these methods should not 
contain code specific to a particular type of CSP, such as N-queens.
*/

public class CSP_rep
{
	/**
	 * A finite list of the variables of the CSP.
	 */
    List<CSP_Variable> variables;
    
	/**
	 * A finite domain of values that all variables of the CSP range over.
	 */
    CSP_Domain domain;

    /**
	 * A finite list of the binary constraints of the CSP.  
	 * Each constraint ranges over a pair of variables in VARIABLES. 
	 */
	 List<CSP_Constraint> constraints;


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
	 public CSP_rep()
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
		for (CSP_Variable v : variables) {

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
		variables.get(i).copy_domain_values();
		variables.get(i).create_iterator_for_domain_values_copy();
		for (;i>-1&&i<variables.size();) {
			if (variables.get(i).select_from_domain_values_copy() == null) {
				variables.get(i).restore_domain_values_from_copy();
				i--;
			} else {
				if (i == variables.size()-1) {
					break;
				}
				i++;
				variables.get(i).copy_domain_values();
				variables.get(i).create_iterator_for_domain_values_copy();
			}
		}
		if (i == -1) {
			return false;
		}
		else return true;
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
		initializeDomainCache();
		int i = 0;
    	variables.get(i).copy_domain_values();
		initializeDomainVersions(i);
		while (i > -1 && i < variables.size()) {
			if (variables.get(i).select_from_domain_values_copy_fc(variables, i) == null) {
				i--;
			} else {
				i++;
				initializeDomainVersions(i);
			}
		}
		if (i < 0) {
			return false;
		}
		return true;
    }

	/**
	 * create new version at the ith level for each variable
	 * @param i the level number
	 */
	public void initializeDomainVersions(int i) {
		for (CSP_Variable var :
				variables) {
			var.domain.initializeDomainVersions(i);
		}
	}

	/**save initial domains to cache via copy_domain_values
	 *
	 */
	public void initializeDomainCache() {
		for (CSP_Variable var : variables) {
			var.copy_domain_values();
		}
	}
}
