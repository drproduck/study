/**
 * Created by Khiem on 1/14/2017.
 */
public class birthdayparadox {
    public static double birthdayProb(int n) {
        int numDay = 365;
        double NOTprob = 1;
        if (n > 364) {
            return 1;
        } else {
            while (n > 0) {
                NOTprob *= (numDay)/365D;
                numDay--;
                n--;
            }
            return 1 - NOTprob;
        }
    }

    public static void main(String[] args) {
        System.out.println(birthdayProb(100));
    }
}
