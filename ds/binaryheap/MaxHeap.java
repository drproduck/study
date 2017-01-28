package binaryheap;

import java.util.Arrays;
public class MaxHeap < T extends Comparable < ? super T >>
{
    private T [] heap; // array of heap entries
    private int lastIndex; // index of last entry
    private static final int DEFAULT_INITIAL_CAPACITY = 25;
    public MaxHeap () {
        this (DEFAULT_INITIAL_CAPACITY); // call next constructor
    } // end default constructor


    public MaxHeap (int initialCapacity) {
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
        while ((parentIndex > 0) && newEntry.compareTo (heap [parentIndex]) > 0) {
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
    

    public T removeMax () {
        T root = null;
        if (!isEmpty ()) {
	    root = heap [1]; // return value
            heap [1] = heap [lastIndex]; // form a semiheap
            lastIndex--; // decrease size
            reheap (1); // transform to a heap
        } // end if
        return root;
    } // end removeMax
    
    
    public T getMax () {
        T root = null;
        if (!isEmpty ())
            root = heap [1];
        return root;
    } // end getMax

    
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
            int largerChildIndex = leftChildIndex; // assume larger
            int rightChildIndex = leftChildIndex + 1;
            if ((rightChildIndex <= lastIndex) &&
                    heap [rightChildIndex].compareTo (heap [largerChildIndex]) > 0)
            {
                largerChildIndex = rightChildIndex;
            } // end if
            if (orphan.compareTo (heap [largerChildIndex]) < 0)
            {
                heap [rootIndex] = heap [largerChildIndex];
                rootIndex = largerChildIndex;
                leftChildIndex = 2 * rootIndex;
            }
            else
                done = true;
        } // end while
        heap [rootIndex] = orphan;
    } // end reheap
    
} // end MaxHeap

class Edge implements Comparable<Edge>{
    int start, end;
    double weight;
    
    public Edge(int v1,int v2, double w){
        start = v1;
        end = v2;
        weight = w;
    }
    
    public  double compare(Edge edge1, Edge edge2){
        return edge1.weight-edge2.weight;
    }
    
    public int compareTo(Edge that){
        if ((this.weight-that.weight)<0)
            return -1;
        if ((this.weight-that.weight)>0)
            return 1;
        else return 0;
    }
    
}

class test{
    public static void main(String[]args){
        MaxHeap<Edge> heap = new MaxHeap<>();
        Edge edge1 = new Edge(1,2,4.4);
        Edge edge2 = new Edge(2,3,5.5);
        Edge edge3 = new Edge(4,5,6.6);
        heap.add(edge1);
        heap.add(edge2);
        heap.add(edge3);
        System.out.println(heap.getMax().weight);
    }
}
