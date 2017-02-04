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

    public double getValue()
    {
        return value;
    }

    public void updateValue(){
        NeuralNode outn;
        double sum = 0;
        for (Weight outw :
                outWeight) {
            outn = (NeuralNode)outw.outNode;
            sum += outw.weight * outn.delta;
        }
    }

    public void connectsTo(Node other) throws Exception {
        if (other instanceof DummyNode) {
            throw new Exception("Dummy Node doesnt have inweight");
        }
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
