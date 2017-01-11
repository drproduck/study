

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
import java.util.List;
import java.util.ListIterator;

public class CSP_Domain
{
	/**
	 * A finite list of objects denoting the values of the domain.
	 */
    List<Object> domain_values = null;
    List<Object> domain_values_copy = null;
    ListIterator<Object> copied_domain_values_iterator = null;
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
    public List<Object> get_domain_values()
    {
	return domain_values;
    }
    
    public List<List> getDomainVersions() {
		return domainVersions;}
	/**
	 * Method creates a copy of domain values.
	 * Used by recursive BT only.
	 */
    public void copy_domain_values()
    {
	domain_values_copy = domain_values;
    }

	public void initializeDomainVersions(int i) {
		List version = new ArrayList();
		if (i > 0) {
			version.addAll(domainVersions.get(i-1));
		} else if (i == 0) {
			version.addAll(domain_values_copy);
		}
		domainVersions.add(i, version);
	}

	/**
	 * Method restores domain values from copy.
	 */
    public void restore_domain_values_from_copy()
    {
    	domain_values = domain_values_copy;
    }
    
    
    /**
	 * Method creates an iterator over domain_values_copy.
	 * Used by iterative BT.
	 */
    public ListIterator<Object> create_iterator_for_domain_values_copy()
    {
    	copied_domain_values_iterator = domain_values_copy.listIterator();
    	return copied_domain_values_iterator;
    }
    
    
    /**
     * Method returns a preexisting iterator over domain_values_copy.
	 */
   public ListIterator<Object> get_iterator_for_domain_values_copy()
   {
   	return copied_domain_values_iterator;
   }
}
