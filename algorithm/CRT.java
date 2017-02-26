/**
 * Created by drproduck on 2/20/17.
 */
public class CRT {
    public static long CRT(long[] remainder, long[] modulo) {
        int l = remainder.length;
        long m = modulo[0];
        long r = remainder[0];
        for (int i = 1; i < l; i++) {
            long xi = getInverseMod(m, modulo[i]);
            long xj = getInverseMod(modulo[i], m);
            r = xi * m * remainder[i] + xj * modulo[i] * r;
            m = m * modulo[i];
            r %= m;
        }
        return r;
    }

    public static void main(String[] args) {
        long[] a = {2,3, 5};
        long[] b = {5, 13, 17};
        System.out.println(CRT(a, b));
    }

    public static long getInverseMod(long n, long mod) {
        n %= mod;
        long big = mod;
        long small = n;
        long r;
        long q;
        long s1 = 0;
        long s2 = 1;
        long s3;
        while ((r = big%small) !=0){
            q = big/small;
            s3 = s1 - s2*q;
            s1 = s2;
            s2 = s3;
            big = small;
            small = r;

        }
        return s2 = (s2<0)? (s2+mod):s2;
    }
}
