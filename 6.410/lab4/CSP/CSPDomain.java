import java.util.*;

/**
 * Title:        CSPDomain
 * Description:  An abstract class, denoting a finite domain
 * of a CSP.  Define subclass to represent a
 * specific kind of CSP domain.
 * Copyright:    Copyright (c) 2005-2007
 * Company:      MIT
 *
 * @author Andreas Hofmann and Brian Williams
 * @version 2.0
 */

public class CSPDomain
{
	/**
	 * A finite list of objects denoting the values of the domain.
	 */

    List domainValues = null;
    ListIterator<Object> domainValuesIterator = null;
	List<Object> domainValuesCopy = null;
	List[] domainValuesVersion = null;

    boolean debug_print = false;
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

   public void makeDomainValuesIteratorFrom(int version) {
       domainValuesIterator = domainValuesVersion[version].listIterator();
   }

   public void restoreDomainValuesFrom(int version) {
       if (domainValuesVersion[version] == null) {
           return;
       }
       domainValues = new LinkedList();
       for (Object val :
               domainValuesVersion[version]) {
           domainValues.add(val);
       }
   }

   public void copyDomainValuesTo(int version) {
       domainValuesVersion[version] = new LinkedList();
       if (debug_print)
       System.out.printf("values stored in version %d: ", version);
       for (Object val :
               domainValues) {
           domainValuesVersion[version].add(val);
           if (debug_print)
           System.out.print(val.toString()+" ");
       }
       if (debug_print)
       System.out.println("\n");
   }
}
