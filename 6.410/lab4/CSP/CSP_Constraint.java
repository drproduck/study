/**
 * Title:        CSP_Constraint
 * Description:  An abstract class denoting a binary, finite
 *               domain constraint.  Define subclass to represent 
 *               a specific kind of CSP constraint.
 * Copyright:    Copyright (c) 2005-2007
 * Company:      MIT
 * @author Andreas Hofmann and Brian Williams
 * @version 2.0
 */

public class CSP_Constraint
{
	/**
	 * This binary constraint goes from input_variable to output_variable.
	 * Note that this directionality is artificial, these are simply
	 * two distinct variables.
	 */
    CSP_Variable input_variable = null;
    CSP_Variable output_variable = null;
    
	/**
	 * Default constructor for a constraint between input and
	 * output variables.
	 * 
	 * @param input		The input CSP variable of constraint.
	 * @param output	The output CSP variable of constraint.
	 * 	 */
    public CSP_Constraint(CSP_Variable input, CSP_Variable output)
    {
	input_variable = input;
	output_variable = output;
	
	// Associate constraint with its input and output
	// variables.

	input.to_arcs.add(this);
	output.from_arcs.add(this);
    }

	/**
	 * Method tests the consistency of constraint
	 * against the current (potentially restricted) domains
	 * of constraint's input and output variables.
	 * 
	 * By default, all assignments are consistent.
	 * 
	 * @return 	A boolean, which is true when constraint
	 *          is consistent.
	 */
    public boolean consistent()
    {
	return true;
    }
    public boolean consistentWithPruning(){return true;}
}
