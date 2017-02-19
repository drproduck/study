import java.util.Scanner;
import java.util.*;

/**
 * Created by drproduck on 2/17/17.
 */
public class PrintDP {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int M = in.nextInt();
        int l = in.nextInt();
        int[] word_lengths = new int[l+1];
        for (int i = 1; i < l+1; i++) {
            word_lengths[i] = in.nextInt();
        }
        int[] min_at = new int[l+1];
        for (int i = 1; i < l+1; i++) {
            min_at[i] = Integer.MAX_VALUE;
        }
		List<Integer> first_word_index = new ArrayList<>();
        min_at[0] = 0;

        for (int i = 1; i < l+1; i++) {
            int space_left_at_last_line = M;
            int index = i;
            int min_score = Integer.MAX_VALUE;
            int score;
            while (index>=1 && space_left_at_last_line - word_lengths[index] >= 0) {
                space_left_at_last_line = space_left_at_last_line - word_lengths[index];
                index--;
                score = min_at[index] + (int) Math.pow(space_left_at_last_line, 3);
                if (min_score > score) {
                    min_score = score;
                }
                space_left_at_last_line--; //next space
				first_word_index.add(index+1);
				min_at[i] = min_score;
            }
        }
	System.out.println(min_at[l]);
    }
}
