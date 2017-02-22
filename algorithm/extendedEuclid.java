/**
 * Created by drproduck on 2/20/17.
 */
public class extendedEuclid {
    public static long getInverseMod(long n, long mod) {
        long big = (n <= mod) ? mod : n;
        long small = (n<=mod) ? n : mod;
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
