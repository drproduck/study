

/**
 * Title:        CSPTop
 * Description:  Top-level class for solving a CSP.
 * Copyright:    Copyright (c) 2005-2007
 * Company:      MIT
 * @author Andreas Hofmann and Brian Williams
 * @version 2.0
 */



public class CSPTop {

	/**
	 * An instance denoting the CSP to be solved.
	 */
    static CSP_rep current_csp = null;

	/**
	 * Default construct, creates and intializes a 4 Queens CSP.
	 */
    public CSPTop()
    {
	current_csp = new NQueens(5);   // BT search gets rough after 21 queens.
	current_csp.initialize();
    }

	/**
	 * Main method, solves the CSP and prints the solution.
	 */
    public static void main(String[] args)
    {
	new CSPTop();
	//current_csp.print();

	System.out.println("\nSolving CSP using backtrack search, please wait.");
	// Solve the CSP
	boolean solve_res = current_csp.backtrack();

	// Print whether consistent, and if consistent the
	// satisfying assignment.
	System.out.println("Consistent result = " + solve_res);
	System.out.println("Solution:");

	current_csp.print();
	
	System.out.println("\nSolving CSP using backtrack search with forward checking, please wait.");

	// Solve the CSP
	current_csp.initialize();
	solve_res = current_csp.backtrack_fc();

	// Print whether consistent, and if consistent the
	// satisfying assignment.
	System.out.println("Consistent result = " + solve_res);
	System.out.println("Solution:");

	current_csp.print();
    }
}




