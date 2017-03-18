package segmenttree;


import java.util.Arrays;

/**
 * Range Minimum Query
 * Created by Khiem on 10/14/2016.
 */
public class SegmentTree {
    protected int[] st;
    protected int[] a; //original array

    public SegmentTree(int[] a) {
        this.a = a;
        st = new int[a.length * 4];
        build(1, 0, a.length - 1);
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
            st[p] = (a[p1] <= a[p2]) ? p1 : p2;
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

    public void update(int u, int v) {
        if (a[u] != v) {
            a[u] = v;
            update(0, a.length - 1, u);
        }
    }

    private void update(int l, int r, int u) {
        int p = findIndex(u)>>1;
        while (p > 1) {
            st[p] = (a[st[left(p)]] <= a[st[right(p)]]) ? st[left(p)] : st[right(p)];
            p = p>>1;
        }
    }

    public int findIndex(int x) {
        return findIndex(1, 0, a.length - 1, x);
    }

    private int findIndex(int p, int l, int r, int x) {
        if (l == r) {
            if (l!=x) return -1;
            return p;
        } else {
            int mid = (l + r) / 2;
            if (x <= mid) {
                return findIndex(left(p), l, mid, x);
            } else return findIndex(right(p), mid + 1, r, x);
        }
    }

    public static void main(String[] args) {
        int[] a = {18, 17, 13, 19, 15, 11, 20};
        SegmentTree st = new SegmentTree(a);
        System.out.println(Arrays.toString(st.st));
        System.out.println(st.rmq(3, 6));
        System.out.println(st.rmq(4, 6));
        System.out.println(st.rmq(1, 2));
        System.out.println(st.rmq(0,1));
        System.out.println(st.rmq(1,4));
        System.out.println(st.rmq(0,5));
        st.update(1, 12);
        System.out.println(st.rmq(1,2));
        System.out.println(st.rmq(0,1));
        System.out.println(st.rmq(1,4));
        System.out.println(st.rmq(0,5));

        int[] b = {0, 1, 2, 1, 2, 3, 2, 3, 2, 1, 2, 1, 0, 1, 2, 1, 2, 1, 0};
        SegmentTree s = new SegmentTree(b);
        System.out.println(Arrays.toString(s.st));
        System.out.println(s.rmq(5, 10));
    }
}
