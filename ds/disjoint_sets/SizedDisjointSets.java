package disjoint_sets;

/**
 * A simple union-by-size implementation of disjoint sets data structure.
 * array index represents the name of node
 * negative array content: the node is a parent, its absolute value the size of the subtree
 * positive array content: the parent of this node
 * every array content starts with -1: every node initially is a parent with size 1
 * Created by Khiem on 1/14/2017.
 */
public class SizedDisjointSets extends DisjointSets {
    public SizedDisjointSets(int numElements) {
        super(numElements);
        for (int i = 0; i < numElements; i++) {
            parents[i]  = -1;
        }
    }

    public void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) ;
        else if (parents[y] < parents[x]) {
            parents[y] = parents[y] + parents[x];
            parents[x] = y;

        } else {
            parents[x] = parents[x] + parents[y];
            parents[y] = x;

        }
    }
        
    public int find( int x){
        if (parents[x] < 0) {
            return x;
        }
        return find(parents[x]);
    }
    
}
