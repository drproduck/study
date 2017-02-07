import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by drproduck on 2/2/17.
 */
public class Console {
    public static void main(String[] args) {
        Console c = new Console();
        c.repl();
       }

    public void repl() {
    boolean end = false;
        Scanner in = new Scanner(System.in);
         while (!end) {
             String s = in.nextLine();
             if (s.equals("quit")) {
                 end = true;
             } else {
                 try {
                     Expression exp = parse(s);
                     System.out.println("-> " + exp.execute());
                 } catch (Exception e) {
                     System.out.println("Unable to parse. Try again");
                 }
             }
         }
    }
    public static Expression parse(String str) {
            Scanner in = new Scanner(str).useDelimiter("\\s*\\+\\s*");
            Scanner on;
            List<Product> products = new ArrayList<>();
            while (in.hasNext()) {
                on = new Scanner(in.next()).useDelimiter("\\s*\\*\\s*");
                List<Double> factors = new ArrayList<>();
                while (on.hasNext()) {
                    factors.add(on.nextDouble());
                }
                Product p = new Product(factors);
                products.add(p);
            }
            Expression sum = new Sum(products);
            return sum;
    }

}
