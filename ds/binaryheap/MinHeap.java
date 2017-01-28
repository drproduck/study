package binaryheap;

import java.util.*;
import java.util.Arrays;
public class MinHeap < T extends Comparable < ? super T >>
{
    private T [] heap; // array of heap entries
    private int lastIndex; // index of last entry
    private static final int DEFAULT_INITIAL_CAPACITY = 25;
    public MinHeap () {
        this (DEFAULT_INITIAL_CAPACITY); // call next constructor
    } // end default constructor


    public MinHeap (int initialCapacity) {
        // the cast is safe because the new array contains all null entries
        @ SuppressWarnings ("unchecked")
            T [] tempHeap = (T []) new Comparable [initialCapacity + 1];
        heap = tempHeap;
        lastIndex = 0;
    } // end constructor
    

    public void add (T newEntry) {
        lastIndex++;
        ensureCapacity ();
        int newIndex = lastIndex;
        int parentIndex = newIndex / 2;
        while ((parentIndex > 0) && newEntry.compareTo (heap [parentIndex]) < 0) {
            heap [newIndex] = heap [parentIndex];
            newIndex = parentIndex;
            parentIndex = newIndex / 2;
        } // end while
        heap [newIndex] = newEntry;
    } // end add
    
    private void ensureCapacity(){
        if (lastIndex==heap.length){
            T[]tempHeap = heap;
            heap = (T[]) new Comparable[heap.length*2-1];
            heap = tempHeap;
        }
    }
    

    public T removeMin () {
        T root = null;
        if (!isEmpty ()) {
	    root = heap [1]; // return value
            heap [1] = heap [lastIndex]; // form a semiheap
            lastIndex--; // decrease size
            reheap (1); // transform to a heap
        } // end if
        return root;
    } // end removeMax
    
    
    public T getMin () {
        T root = null;
        if (!isEmpty ())
            root = heap [1];
        return root;
    } // end getMin
    
    public T contains(T object){
        for (T entry:heap){
            if (entry.equals(object))
                 return entry;   
        }
        return null;
    }

    
    public boolean isEmpty () {
        return lastIndex < 1;
    } // end isEmpty
    

    public int getSize () {
        return lastIndex;
    } // end getSize
    

    public void clear () {
        for (; lastIndex > -1 ; lastIndex--)
            heap [lastIndex] = null;
        lastIndex = 0;
    } // end clear


    // Private methods 
    private void reheap (int rootIndex) {
        boolean done = false;
        T orphan = heap [rootIndex];
        int leftChildIndex = 2 * rootIndex;
        while (!done && (leftChildIndex <= lastIndex))
        {
            int smallerChildIndex = leftChildIndex; // assume larger
            int rightChildIndex = leftChildIndex + 1;
            if ((rightChildIndex <= lastIndex) &&
                    heap [rightChildIndex].compareTo (heap [smallerChildIndex]) < 0)
            {
                smallerChildIndex = rightChildIndex;
            } // end if
            if (orphan.compareTo (heap [smallerChildIndex]) > 0)
            {
                heap [rootIndex] = heap [smallerChildIndex];
                rootIndex = smallerChildIndex;
                leftChildIndex = 2 * rootIndex;
            }
            else
                done = true;
        } // end while
        heap [rootIndex] = orphan;
    } // end reheap
    
    public void reheap(){
        reheap(1);
    }
    
} // end MinHeap
