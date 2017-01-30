import java.util.List;

/**
 * Created by Khiem on 1/26/2017.
 */
public class NeuralNode {
    public double value;
    public List<Weight> inWeight;
    public List<Weight> outWeight;
    public double delta;
    Function f;

    public NeuralNode(){

    }
    public double getValue(){
        return value = f.squash();
    }

    public double getDelta(){
        double derivative = value * (1 - value);
        double sum = 0;
        NeuralNode outn;
        for (Weight outw :
                outWeight) {
            outn = outw.outNode;
            sum += outw.weight * outn.delta;
        }
        return delta = derivative * sum;
    }
}
