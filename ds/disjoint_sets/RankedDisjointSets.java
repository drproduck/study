package disjoint_sets;

/**
 * Created by Khiem on 1/14/2017.
 */
public class RankedDisjointSets extends DisjointSets {
    public RankedDisjointSets(int numElements) {
        super(numElements);
    }

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

    public int find(int x) {
        if (parents[x] < 0) {
            return x;
        } else {
            return parents[x] = find(parents[x]);
        }

    }
}
