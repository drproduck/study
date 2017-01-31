import java.util.List;

/**
 * Created by Khiem on 1/26/2017.
 */
public class NeuralNode extends Node {
    protected double value;
    private List<Weight> inWeight;
    private List<Weight> outWeight;
    private double delta;
    private double input;
    Function f;

    public NeuralNode(){

    }
    public void setInput(){
        input = f.squash(value);
    }

    public void setValue(){
        NeuralNode outn;
        double sum = 0;
        for (Weight outw :
                outWeight) {
            outn = outw.outNode;
            sum += outw.weight * outn.delta;
        }
    }
}
