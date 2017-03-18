/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package disjoint_sets;

/**
 *
 * @author Khiem
 */
abstract class DisjointSets {

    public int[] getParents() {
        return parents;
    }

    public int getNumElements() {
        return numElements;
    }

    protected int[] parents;
    protected int numElements;

    public DisjointSets(int numElements) {
        this.numElements = numElements;
        parents = new int[numElements];
    }

    abstract void union(int x, int y);

    abstract int find(int x);

    abstract int size(int x);
}
