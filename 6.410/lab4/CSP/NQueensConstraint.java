package csp;

/**
 * Title:        NQueensConstraint
 * Description:  A class denoting a binary constraint 
 *               between two queens in an N Queens CSP.
 * Copyright:    Copyright (c) 2005-2007
 * Company:      MIT
 * @author Andreas Hofmann and Brian Williams
 * @version 2.0
 */


import java.util.List;
import java.util.ListIterator;


public class NQueensConstraint extends CSP_Constraint
{
	/**
	 * Boolean flag, which is <code>True</code> iff
	 * debugging information is printed for constraint.
	 */
    boolean debug_print = false;

	/**
	 * Constructor for N Queens.
	 */
    public NQueensConstraint(NQueensVariable inp, NQueensVariable outp)
    {
	super(inp, outp);
    }

	/**
	 * Method tests the consistency of an N queens
	 * constraint between two queens.  Returns <code>True</code>
	 * if there is no row assignment to the two queens
	 * such that the queens can attack each other, either
	 * horizontally or diagonally.
	 * 
	 * @return 	A boolean, which is <code>True</code> when 
	 * 		    constraint is consistent.
	 */
    public boolean consistent()
    {
    	// Get the two queens whose attack is represented by
    	// this constraint
    	NQueensVariable input_var = (NQueensVariable)input_variable;
    	NQueensVariable output_var = (NQueensVariable)output_variable;

    	// Get the columns that the two queens reside on.
    	int input_col = input_var.col;
    	int output_col = output_var.col;

    	// Print out the columns of the two queens.
    	if (debug_print)
    		System.out.println("In NQueensConstraint consistent, input_col = " + input_col + " output_col = " + output_col);

    	// Get the remaining allowed row values for the two queens.
    	List input_domain_values = input_var.get_domain_values();
    	List output_domain_values = output_var.get_domain_values();

    	if (debug_print)
    		System.out.println("input_values = " + input_domain_values + " output_values = " + output_domain_values);

    	// Find at least one assignment to the input/output variables
    	// that is consistent.  More specifically, find at least one
    	// assignment of rows to the two queens, within their current domains,
    	// such that the two queens cannot attack each other, horizontally
    	// or diagonally.
    	ListIterator input_iterator = input_domain_values.listIterator();
    	while (input_iterator.hasNext()) {
    		
    		// Succesively select a row for the input queen from its domain.
    		Integer input_domain_value = (Integer)input_iterator.next();

    		ListIterator output_iterator = output_domain_values.listIterator();
    			while (output_iterator.hasNext()) {
    				
    				// Successively select a row for the output queen from its domain.
    				Integer output_domain_value = (Integer)output_iterator.next();

    				int input_row = input_domain_value.intValue();
    				int output_row = output_domain_value.intValue();

    				// Print out the selected rows for the two queens.
    				if (debug_print)
    					System.out.println("input_row = " + input_row + " output_row = " + output_row);

    				// Check that the queens can't attack each other at
    				// these row assignments.  This holds only if
					// a) the rows are not the same, and 
					// b) the positions are not on the same diagonal.
    				if ((input_row != output_row) &&
    						(Math.abs(input_col - output_col) != Math.abs(input_row - output_row))) 
    					// The current row assignments work, return consistent.
    					return true;
    				}
    	}
    	// Return that the domain values for the two queens
    	// are inconsistent.  Tried all row assignments to the two queens, 
    	// and in each case the queens were able to attack each other.
    	return false;
    }
}