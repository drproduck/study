

/**
 * Title:        CSP_Domain
 * Description:  An abstract class, denoting a finite domain
 *               of a CSP.  Define subclass to represent a 
 *               specific kind of CSP domain.
 * Copyright:    Copyright (c) 2005-2007
 * Company:      MIT
 * @author Andreas Hofmann and Brian Williams
 * @version 2.0
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CSP_Domain
{
	/**
	 * A finite list of objects denoting the values of the domain.
	 */

    List<Object> domainValues = null;
    ListIterator<Object> domainValuesIterator = null;
	/**
	 * this list of domain versions is for forward checking where we have to keep track of
	 * available values in the domains of each variables (as result of pruning and backtracking)
	 */
	List<List> domainVersions = new ArrayList<>();

	/**
	 * Default constructor.  Does nothing.
	 */
    public CSP_Domain()
    {

    }

	/**
	 * Method returns the values of Domain.
	 * 
	 * @return 	A list of objects.
	 */
    public List<Object> getDomainValues()
    {
	return domainValues;
    }

	/**
	 * return domain versions
	 * @return the original version if using normal backtrack
	 * 			a list of versions if using backtrack with forward checking
	 */
	public List<List> getDomainVersions() {
		return domainVersions;}

	public List getDomainVersion(int n) {
		return domainVersions.get(n);
	}

	public void initializeDomainVersions(int i) {
		List version = new ArrayList();
		if (i > 0) {
			version.addAll(domainVersions.get(i-1));
		} else if (i == 0) {
			version.addAll(domainValues);
		}
		domainVersions.add(i, version);
	}

    /**
     * Method returns a preexisting iterator over domain_values_copy.
	 */
   public Iterator<Object> getDomainValuesIterator()
   {
   	return domainValuesIterator;
   }

   public void initializeDomainValuesIterator() {
	   domainValuesIterator = domainValues.listIterator();
   }
}
