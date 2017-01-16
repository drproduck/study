

/**
 * Title:        NQueensVariable
 * Description:  A variable of an N Queens problem, denoting
 *               the row position of a queen associated with
 *               a particular column.
 * Copyright:    Copyright (c) 2005-2007
 * Company:      MIT
 * @author Andreas Hofmann and Brian Williams
 * @version 2.0
 */

public class NQueensVariable extends CSPVariable
{
	/**
	 * The column of the queen that variable denotes.
	 */
    int col = 0;

	/**
	 * Constructor for N Queens variable.
	 * 
	 * @param	c1	Is an <code>integer</code>, denoting the column of variable's queen.
	 * @param	dm	Is a <code>CSPDomain</code>, denoting the allowed rows
	 * 				where variable's queen may be placed.
	 */
    public NQueensVariable(int cl, CSPDomain dm)
    {
	col = cl;
	domain = dm;
    }
}
