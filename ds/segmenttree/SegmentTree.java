package segmenttree;
/**
 * Created by Khiem on 10/14/2016.
 */
public class SegmentTree {
    private int[] st;
    private int[] a; //original array

    public SegmentTree(int[] a) {
        this.a  = a;
        st = new int[a.length*4];
        build(1, 0, a.length-1);
    }

    private int left(int p) {
        return (p << 1);
    }

    private int right(int p) {
        return (p << 1) + 1;
    }

    public void build(int p, int l, int r) {
        if (l == r) {
            st[p] = l;
        } else {
            build(left(p), l, (l + r) / 2);
            build(right(p), (l + r) / 2 + 1, r);
            int p1 = st[left(p)], p2 = st[right(p)];
            st[p] = (a[p1] <= a[2]) ? p1 : p2;
        }
    }

    public int rmq(int i, int j) {
        return rmq(1, 0, a.length - 1, i, j);

    }
    public int rmq(int p, int l, int r, int i, int j) {
        if (i > r || j < l) {
            return -1;
        }
        if (i <= l && j >= r) {
            return st[p];
        }
        int p1 = rmq(left(p), l, (l + r) / 2, i, j);
        int p2 = rmq(right(p), (l + r) / 2 + 1, r, i, j);
        if (p1 == -1) {
            return p2;
        }
        if (p2 == -1) {
            return p1;
        }
        return (a[p1] <= a[p2]) ? p1 : p2;
    }


    public static void main(String[] args) {
        int[] a = {18, 17, 13, 19, 15, 11, 20};
        SegmentTree st  = new SegmentTree(a);
        System.out.println(st.rmq(4,6));

    }
}
