

/**
 * Title:        CSPVariable
 * Description:  An abstract class, denoting a finite domain
 * variable of a constraint satisfaction problem.
 * Define subclass to represent a specific kind
 * of CSP variable.
 * Copyright:    Copyright (c) 2005-2007
 * Company:      MIT
 *
 * @author Andreas Hofmann and Brian Williams
 * @version 2.0
 */

import java.util.*;


public class CSPVariable {
    /**
     * The finite domain of values that the CSP variable
     * ranges over.
     */
    CSPDomain domain = null;

    /**
     * domain versions for forward checking
     */
    List<CSPDomain> domainVersion = null;
    /**
     * A list of all contraints that include this variable as its
     * input variable.
     */
    List<CSPConstraint> fromArcs = new ArrayList<CSPConstraint>();
    /**
     * A list of all constraints that include this variable as its
     * output variable.
     */
    List<CSPConstraint> toArcs = new ArrayList<CSPConstraint>();
    ;
    /**
     * Boolean flag, which is <code>True</code> iff
     * debugging information is printed for variable.
     */
    boolean debug_print = false;


    /**
     * Default constructor.  Does nothing.
     */
    public CSPVariable() {

    }


    /**
     * Method returns the domain of variable.
     *
     * @return Returns a CSPDomain for variable.
     */
    public CSPDomain getDomain() {
        return domain;
    }


    /**
     * Method returns the domain values of variable.
     *
     * @return Returns a list of objects,
     *          denoting the domain values.
     */
    public List<Object> getDomainValues() {
        return domain.domainValues;
    }

    /**
     * used for forward checking
     * @param version
     * @return domain values of this particular version
     */
    public CSPDomain getDomainVersion(int version) {
        return domainVersion.get(version);
    }

    /**
     * Method assigns domain_value is the
     * value of this variable.
     *
     * @parm domain_value    An object that is the element of
     *                      the domain of variable.
     */
    public void assign(Object domainValue) {
        domain.assign(domainValue);
    }

    public void assign(Object domainValue, int version) {
        domainVersion.get(version).assign(domainValue);
    }

    /**
     * Method restores domain values of variable from copy.
     * Used by iterative BT.
     */
    public void restoreDomainValues() {
        domain.restoreDomainValue();
    }

    public void copyDomainValues() {
        domain.copyDomainValues();
    }

    public void initializeDomainValueIterator() {
        domain.initializeDomainValuesIteratorFromCopy();
    }


    /**
     * Method returns a preexisting iterator over variable domain's
     * domain_values_copy.
     */
    public Iterator<Object> getDomainValuesIterator() {
        return domain.getDomainValuesIterator();
    }


    /**
     * Method selects a consistent value for
     * variable from its copied domain.
     *
     * @return a consistent value if it exists, otherwise null
     */
    public Object select() {

        Iterator<Object> valIterator = getDomainValuesIterator();

        while (valIterator.hasNext()) {
            Object currentVal = valIterator.next();

            // Print out the assignment being tried.
            if (debug_print)
                System.out.println("In select, val = " + currentVal);

            // Assign the next domain value to the current variable.
            assign(currentVal);

            // Check that the assignment to variable is consistent
            // with all constraints involving variable.
            if (consistent()) {

                // When successful, return the current value.
                return currentVal;
            }

            // If the assignment of current value to variable was inconsistent,
            // then move down the domain.
        }

        // No value of variable's domain was consistent, return nothing.
        return null;
    }

    /**
     * Method selects a consistent value for
     * variable from its copied domain.
     * @param    vars    variables
     * @param    i        index of this variable
     * @return a consistent value if it exists, otherwise null
     */
    public Object selectFC(int version) {
        Iterator domainIterator = domainVersion.get(version).getDomainValuesIterator();
        while (domainIterator.hasNext()) {
        Object currentVal = domainIterator.next();
        assign(currentVal, version);
        if (consistentFC()) {
            return currentVal;
        } else return null;
    }

    /**
     * Check consistency of unassigned domain values with this assigned variable,
     * considering only arc directed towards this variable
     * if true, unassigned variables' domains will be pruned.
     * if false, restore domain of unassigned variables.
     * @return true iff variable's domain values are consistent
     */
    public boolean consistentFC(int version) {
        ListIterator<CSPConstraint> fromArcIterator = fromArcs.listIterator();
        while (fromArcIterator.hasNext()) {
            CSPConstraint to_arc = fromArcIterator.next();
            if (!to_arc.prune(version)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method checks the consistency of the domain values of variable against
     * all constraints involving variable. Returns <code>True</code> iff
     * consistent.
     *
     * @return <code>True</code> iff variable's domain values are consistent
     *         against the local constraints.
     */
    public boolean consistent() {
        // Check consistency of all constraints involving this variable.

        // Iterate over toArcs, checking that each to_arc is consistent.
        ListIterator<CSPConstraint> toMeIterator = toArcs.listIterator();
        while (toMeIterator.hasNext()) {
            CSPConstraint toArc = toMeIterator.next();
            if (!(toArc.consistent())) return false;
        }

        // Iterate over fromArcs, checking that each from_arc is consistent.
        ListIterator<CSPConstraint> from_arc_iterator = fromArcs.listIterator();
        while (from_arc_iterator.hasNext()) {
            CSPConstraint fromArc = from_arc_iterator.next();
            if (!(fromArc.consistent())) return false;
        }

//	 Return false if any arc is inconsistent, otherwise return True.
        return true;
    }


}
