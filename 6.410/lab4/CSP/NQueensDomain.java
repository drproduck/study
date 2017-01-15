

/**
 * Title:        NQueensDomain
 * Description:  The domain of an N queens variable, denoting
 * the rows that a queen, associated with a
 * particular column, can reside.
 * Copyright:    Copyright (c) 2005-2007
 * Company:      MIT
 *
 * @author Andreas Hofmann and Brian Williams
 * @version 2.0
 */


import java.util.*;

public class NQueensDomain extends CSP_Domain {

    /**
     * Constructor for NQueensDomain.  Creates a domain
     * with elements 0 through size - 1, where size is
     * the number of queens in the problem.
     *
     * @param    size    An integer, denoting the number of
     * 				    queens in the CSP.
     */
    public NQueensDomain(int size) {
        //super();
        domainValues = new ArrayList<Object>();

        for (int row = 0; row < size; row++) {
            domainValues.add(new Integer(row));
        }

    }

}

