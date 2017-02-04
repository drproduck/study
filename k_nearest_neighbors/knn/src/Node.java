import java.util.ArrayList;
import java.util.List;

/**
 * Created by drproduck on 1/29/17.
 */
public abstract class Node {
    protected double value;
    public List<Weight> inWeight;
    public List<Weight> outWeight;
    protected double delta;
    protected double input;
    Function f;
    public Node() {
        inWeight = new ArrayList<>();
        outWeight = new ArrayList<>();
        f = Function.Sigmoid;
    }
    protected double updateInput(){
        return input = f.squash(value);
    }

    protected void updateDelta(){
        delta = input * (1 - input) * getValue();
    }

    public double getDelta() {
        return delta;
    }

    public double getValue() {
        return value;
    }

    public void connectsTo(Node other) {
        Weight w = new Weight(this, other);
        this.outWeight.add(w);
        other.inWeight.add(w);
    }

    public List<Double> getWeights() {
        List<Double> weights = new ArrayList();
        for (Weight w :
                inWeight) {
            weights.add(w.getWeight());
        }
        return weights;
    }
}
