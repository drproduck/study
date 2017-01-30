import java.util.List;

/**
 * Created by drproduck on 1/29/17.
 */
public class LogisticFunction extends Function {
    public LogisticFunction(List<Weight> weights) {
        super(weights);
    }

    double squash(){
        double sum  = 0;
        NeuralNode inNode;
        for (Weight w:
             weights) {
            inNode = w.inNode;
            sum += w.weight * inNode.value;
        }
        return 1 / (1 + Math.exp(-1 * sum));
    }

    static double dot(Weight weight){
        NeuralNode inNode = weight.inNode;
        return weight.weight * inNode.value;
    }
}

