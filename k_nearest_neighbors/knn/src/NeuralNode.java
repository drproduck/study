import java.util.List;

/**
 * Created by Khiem on 1/26/2017.
 */
public class NeuralNode {
    public double value;
    public List<Weight> inWeight;
    public List<Weight> outWeight;
    public double delta;
    public double input;
    Function f;

    public NeuralNode(){

    }
    public double getInput(){
        return input = f.squash();
    }

    public double getValue(){
        NeuralNode outn;
        double sum = 0;
        for (Weight outw :
                outWeight) {
            outn = outw.outNode;
            sum += outw.weight * outn.delta;
        }
        return sum;
    }
    public double getDelta(){
        return input * (1 - input) * getValue();
    }
}
