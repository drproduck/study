import java.util.List;

/**
 * Created by drproduck on 2/2/17.
 */
public class Product implements Expression{
    private List<Double> factors;

    public Product(List<Double> factors) {
        this.factors = factors;
    }

    public double execute() {
        double p = 1;
        for (Double factor :
                factors) {
            p *= factor;
        }
        return p;
    }
}
