/**
 * Created by drproduck on 2/22/17.
 */
public class NumberTheory {
    static int root(int n, int k) {
        int k1 = k - 1;
        int s = n + 1;
        int u = n;
        while (u < s) {
            s = u;
            u = (u * k1) + n;
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println(root(9, 2));
    }
}
