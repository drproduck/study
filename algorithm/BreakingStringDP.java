/**
 * Created by drproduck on 2/26/17.
 */
public class BreakingStringDP {
    static int[] cut;
    static int[][] dp;
    public static void main(String[] args){
        int n = 20;
        int k = 3;
        cut = new int[3];
        cut[0] = 1;
        cut[1] = 7;
        cut[2] = 9;
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println(recurse(0, n-1));
    }

    static int recurse(int from, int to) {
        if (dp[from][to] != -1) {
            return dp[from][to];
        } else {
            int left_cut = search_left(from);
            int right_cut = search_right(to);
            if (left_cut > right_cut) {
                return 0;
            }
            if (left_cut == right_cut) {
                return to - from + 1;
            }
            int min = Integer.MAX_VALUE;
            for (int i = left_cut; i <= right_cut && i < to; i++) {
                min = Integer.min(min, to - from + 1 + recurse(from, cut[i]) + recurse(cut[i] + 1, to));
            }
            dp[from][to] = min;
            return min;
        }
    }

    static int search_left(int f) {
        return search_left(f, 0, cut.length - 1);
    }

    static int search_left(int f, int l, int r) {
        if (l == r) {
            return cut[l];
        } else {
            int mid = (l + r) / 2;
            if (cut[mid] > f) {
                return search_left(f, l, mid);
            } else {
                return search_left(f, mid + 1, r);
            }
        }
    }

    static int search_right(int t) {
        return search_right(t, 0, cut.length - 1);
    }

    static int search_right(int t, int l, int r) {
        if (l == r) {
            return cut[l];
        } else {
            int mid = (l + r) / 2;
            if (cut[mid] < t) {
                return search_right(t, mid + 1, r);
            } else {
                return search_right(t, l, mid);
            }
        }
    }
}
