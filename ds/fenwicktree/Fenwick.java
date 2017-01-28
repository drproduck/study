package fenwicktree;

/**
 * Created by Khiem on 1/19/2017.
 */
public class Fenwick {
    int[] ft;

    public Fenwick(int n) {
        ft = new int[n + 1];
    }

    public Fenwick(int[] array) {
        ft = new int[array.length];
        for (int i = 1; i < array.length; i++) {
            update(i, array[i]);
        }
    }

    int LSOne(int b) {
        return (b & (-b));
    }

    int rsq(int b) {
        int sum = 0;
        for (; b > 0; b -= LSOne(b)) {
            sum += ft[b];

        }
        return sum;
    }

    int rsq(int a, int b) {
        return rsq(b) - ((a == 1) ? 0 : rsq(a - 1));
    }

    /**
     * adjust the frequency of k by an amount v
     * @param k the k-element
     * @param v an amount v
     */
    void update(int k, int v) {
        for (; k < ft.length; k += LSOne(k)) {
            ft[k] += v;
        }
    }

    public static void main(String[] args) {
        int[] a = {0, 2, 4, 6, 8, 10, 1, 3, 5, 7, 9};
        Fenwick fw = new Fenwick(a);
        System.out.println(fw.rsq(2, 6));
    }
}
