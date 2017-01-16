

/**
 * Title:        CSPDomain
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

public class CSPDomain
{
	/**
	 * A finite list of objects denoting the values of the domain.
	 */

    List<Object> domainValues = null;
    ListIterator<Object> domainValuesIterator = null;
	List<List> domainValuesCopy = null;
	/**
	 * this list of domain versions is for forward checking where we have to keep track of
	 * available values in the domains of each variables (as result of pruning and backtracking)
	 */

	/**
	 * Default constructor.  Does nothing.
	 */
    public CSPDomain()
    {
    }

	public void assign(Object value) {
		domainValues = new ArrayList<>();
		domainValues.add(value);
	}

	/**
	 * Method returns the values of Domain.
	 * @return 	A list of objects.
	 */
    public List<Object> getDomainValues()
    {
	return domainValues;
    }
    /**
     * Method returns a preexisting iterator over domain_values_copy.
	 */
   public Iterator<Object> getDomainValuesIterator()
   {
   	return domainValuesIterator;
   }

   public void initializeDomainValuesIteratorFromCopy() {
	   domainValuesIterator = domainValuesCopy.get(0).listIterator();
   }

   public void copyDomainValues() {
	   domainValuesCopy.get(0) = domainValues;
   }

   public void restoreDomainValue() {
	   domainValues = domainValuesCopy;
   }
}
