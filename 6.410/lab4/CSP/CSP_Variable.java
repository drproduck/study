package csp;

/**
 * Title:        CSP_Variable
 * Description:  An abstract class, denoting a finite domain
 *               variable of a constraint satisfaction problem.
 *               Define subclass to represent a specific kind 
 *               of CSP variable.
 * Copyright:    Copyright (c) 2005-2007
 * Company:      MIT
 * @author Andreas Hofmann and Brian Williams
 * @version 2.0
 */

import java.util.*;


public class CSP_Variable
{
	/**
	 * The finite domain of values that the CSP variable
	 * ranges over.
	 */
    CSP_Domain domain = null;

    
	/**
	 * A list of all contraints that include this variable as its
	 * input variable.
	 */
    List<CSP_Constraint> from_arcs = new ArrayList<CSP_Constraint>();

    
	/**
	 * A list of all contraints that include this variable as its
	 * output variable.
	 */
    List<CSP_Constraint> to_arcs = new ArrayList<CSP_Constraint>();

    
	/**
	 * Boolean flag, which is <code>True</code> iff
	 * debugging information is printed for variable.
	 */
    boolean debug_print = false;
    
    
	/**
	 * Default constructor.  Does nothing.
	 */
    public CSP_Variable()
    {

    }

    
	/**
	 * Method returns the domain of variable.
	 * 
	 * @return	Returns a CSP_Domain for variable.
	 */
    public CSP_Domain get_domain()
    {
	return domain;
    }

    
	/**
	 * Method returns the domain values of variable.
	 * 
	 * @return	Returns a list of objects, 
	 *          denoting the domain values.
	 */
    public List<Object> get_domain_values()
    {
	return domain.domain_values;
    }
    
    
	/**
	 * Method assigns domain_value is the
	 * value of this variable.
	 * 
	 * @parm domain_value	An object that is the element of 
	 *                      the domain of variable.
	 */
    public void assign(Object domain_value)
    {
		// An assignment is represented by reducing the domain
		// to a list of one value.
	List<Object> values = new ArrayList<Object>();
	values.add(domain_value);
	domain.domain_values = values;  // Replaces old domain
    }

    
	/**
	 * Method creates a copy of the current
	 * values of domain.
	 */
    public void copy_domain_values(){
    	domain.copy_domain_values();
    }
    
    
	/**
	 * Method restores the values of variable's domain to
	 * VALUES.
	 */
    public void restore_domain_values_to(List<Object> values)
    {
	domain.domain_values = values;  // Replaces old domain w copy
    }

   
	/**
	 * Method restores domain values of variable from copy.
	 * Used by iterative BT.
	 */
    public void restore_domain_values_from_copy()
    {
	domain.restore_domain_values_from_copy();
    }
    
	/**
	 * Method restores domain values of variable from copy.
	 * Used by iterative BT.
	 */
    public void create_iterator_for_domain_values_copy()
    {
	domain.create_iterator_for_domain_values_copy();
    }
   
    
    /**
     * Method returns a preexisting iterator over variable domain's
     * domain_values_copy.
	 */
   public ListIterator<Object> get_iterator_for_domain_values_copy()
   {
   	return domain.get_iterator_for_domain_values_copy();
   }
   
   
	/**
	 * Method selects a consistent value for
	 * variable from its copied domain.
	 * 
	 * @return	a consistent value if it exists, otherwise null
	 */   
    public Object select_from_domain_values_copy(){

    	ListIterator<Object> val_iterator = get_iterator_for_domain_values_copy();
    	
    	while (val_iterator.hasNext()) {
        	Object current_val = val_iterator.next();

    		// Print out the assignment being tried.
           	if (debug_print)
           		System.out.println("In select_from_domain_values_copy, val = " + current_val);
           	
        	// Assign the next domain value to the current variable.
        	assign(current_val);

        	// Check that the assignment to variable is consistent
        	// with all constraints involving variable.
        	if (consistent()) {

        		// When successful, return the current value.
        		return current_val;
	    }	

        	// If the assignment of current value to variable was inconsistent,
        	// then move down the domain.
	}

        // No value of variable's domain was consistent, return nothing.
       	return null;
    }
    
    /**
	 * Method selects a consistent value for
	 * variable from its copied domain.
	 * @param	vars 	variables
	 * @param	i		index of this variable
	 * @return			a consistent value if it exists, otherwise null 
	 */   
    public Object select_from_domain_values_copy_fc(List<CSP_Variable> vars,int i)
    {
		return null;
	}
    

	/**
	 * Method checks the consistency of the domain values of variable against
	 * all constraints involving variable. Returns <code>True</code> iff
	 * consistent.
	 * 
	 * @return <code>True</code> iff variable's domain values are consistent
	 *         against the local constraints.
	 */
    public boolean consistent()
    {
	// Check consistency of all constraints involving this variable.

	// Iterate over to_arcs, checking that each to_arc is consistent.
	ListIterator<CSP_Constraint> to_arc_iterator = to_arcs.listIterator();
	while (to_arc_iterator.hasNext()) {
	    CSP_Constraint to_arc = to_arc_iterator.next();
	    if (!(to_arc.consistent())) return false;
	}

	// Iterate over from_arcs, checking that each from_arc is consistent.
	ListIterator<CSP_Constraint> from_arc_iterator = from_arcs.listIterator();
	while (from_arc_iterator.hasNext()) {
	    CSP_Constraint from_arc = from_arc_iterator.next();
	    if (!(from_arc.consistent())) return false;
	}

//	 Return false if any arc is inconsistent, otherwise return True.
	return true;
    }

      
}
