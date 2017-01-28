/**
 * Created by Khiem on 1/26/2017.
 */
public class Weight {
    public NeuralNode inNode;
    public NeuralNode outNode;
    public double weight;

    public Weight(NeuralNode i, NeuralNode o, double weight) {
        inNode = i;
        outNode = o;
        this.weight = weight;
    }
}
