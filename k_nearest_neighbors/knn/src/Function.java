import java.util.List;

/**
 * Created by drproduck on 1/29/17.
 */
public abstract class Function {
    List<Weight> weights;
    public Function(List<Weight> weights){
        this.weights = weights;
    }
    abstract double squash();
}
