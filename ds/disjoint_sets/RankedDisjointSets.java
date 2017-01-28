package disjoint_sets;

/**
 * A simple union-by-rank-with-path-compression implementation of disjoint sets data structure.
 * array index represents the name of node
 * negative array content: the node is a parent, its absolute value the size of the subtree
 * positive array content: the parent of this node
 * every array content starts with -1: every node initially is a parent with rank 1
 * Created by Khiem on 1/14/2017.
 */
public class RankedDisjointSets extends DisjointSets {
    public RankedDisjointSets(int numElements) {
        super(numElements);
        for (int i = 0; i < numElements; i++) {
            parents[i] = -1;
        }
    }

    /**
     * union-by-rank: each node initially has rank 1.Each time 2 trees are concatenated,
     * the root of the tree with smaller rank will be linked to the root of the bigger one
     * rank is not necessarily height as path compression with alter heights online
     */
    public void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (parents[y] < parents[x]) {
            parents[x] = y;
        } else {
            if (parents[x] == parents[y]) {
                parents[x]--;
            }
            parents[y] = x;
        }

    }

    /**
     * path-compression: Each time 2 trees are joined, the nodes that are traversed in the smaller tree
     * will be linked to the root of the larger tree. This makes the tree less deep, allowing faster
     * access in next find. It has been shown that the amortized find is practically below 5 (though unbounded)
     * @param x
     * @return
     */
    public int find(int x) {
        if (parents[x] < 0) {
            return x;
        } else {
            return parents[x] = find(parents[x]);
        }

    }
}
