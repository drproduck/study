

/**
 * Title:        NQueens
 * Description:  A Class for denoting an N Queens CSP,
 * for natural number N.
 * <p>
 * The problem is to place N queens on an N by N
 * board without allowing the queens to attack each other.
 * In this object N is denoted SIZE.
 * <p>
 * In this encoding, the ith variable denotes a
 * queen located in the ith column of an N x N
 * choess board, number starting from 0 on the left.
 * <p>
 * The value of a variable denotes the row where that
 * queen resides, where rows are numbered starting at 0.
 * <p>
 * The variable domain is the set of rows where the Queen
 * can be placed, this is initially all N rows.
 * <p>
 * A constraint is introduced between each pair of variables,
 * specifying that the corresponding queens cannot be placed in
 * position in which they can attack each other
 * horizontally or diagonally (vertically is already covered
 * by placing each queen in its own column).
 * <p>
 * Copyright:    Copyright (c) 2005-2007
 * Company:      MIT
 *
 * @author Andreas Hofmann and Brian Williams
 * @version 2.0
 */


import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class NQueens extends CSPRep {

    /**
     * Number of Queens in the problem.
     */
    int size = 0;

    /**
     * Construct, records the number of queens in the CSP.
     *
     * @param sz    An integer, denoting the number of queens
     * 				in the CSP.
     */
    public NQueens(int sz) {
        size = sz;
    }

    /**
     * Initialization method.  Creates an N Queens CSP,
     * where is the size of the CSP.
     **/
    public void initialize() {
        // Initialize the N Queens problem according to SIZE (= N).

        // Generate SIZE variables, with corresponding domains.
        // All variables have the same domain values,
        // (0, ... SIZE-1)
        variables = new ArrayList<CSPVariable>();
        domain = new NQueensDomain(size);
        constraints = new ArrayList<CSPConstraint>();

        for (int col = 0; col < size; col++) {
            NQueensDomain var_domain = new NQueensDomain(size);
            NQueensVariable new_var = new NQueensVariable(col, var_domain);
            variables.add(new_var);
        }

        // Generate a constraint between each pair of variables,
        // specifying that the corresponding queens cannot be placed in
        // a position in which they can attack each other
        // horizontally or diagonally (vertically is already covered
        // by placing each queen in its own column).

        ListIterator var1_iterator = variables.listIterator();

        while (var1_iterator.hasNext()) {
            NQueensVariable var1 = (NQueensVariable) var1_iterator.next();

            ListIterator var2_iterator = variables.listIterator();

            while (var2_iterator.hasNext()) {
                NQueensVariable var2 = (NQueensVariable) var2_iterator.next();

                if (var1 != var2)
                    constraints.add(new NQueensConstraint(var1, var2));
            }
        }
    }

    /**
     * Print method.  Prints the current state of the N Queens
     * problem, in terms of the unpruned domain values (allowed
     * rows) for each Queen in the CSP.
     **/
    public void print() {
        // For each queen, print the queen's column, followed
        // by the rows on which the queen may be placed, according
        // to the queen's variable's domain values.
        ListIterator var_iterator = variables.listIterator();

        while (var_iterator.hasNext()) {
            NQueensVariable current_var = (NQueensVariable) var_iterator.next();

            System.out.println("Queen on column: " + current_var.col);

            List values = current_var.domain.domainValues;
            ListIterator val_iterator = values.listIterator();

            System.out.println("Can be placed on rows: ");
            while (val_iterator.hasNext()) {
                Integer current_val = (Integer) val_iterator.next();

                System.out.print(" " + current_val);
            }
            System.out.println();
        }
    }
}
