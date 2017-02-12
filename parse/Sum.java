import java.util.List;

/**
 * Created by drproduck on 2/2/17.
 */
public class Sum  implements Expression {
    public List<Product> products;

    public Sum(List<Product> products) {
        this.products = products;
    }

    public double execute() {
        double sum = 0;
        for (Product p :
                products) {
            sum += p.execute();
        }
        return sum;
    }
}
