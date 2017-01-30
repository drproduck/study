/**
 * Created by Khiem on 1/26/2017.
 */
public class Weight {
    public NeuralNode inNode;
    public NeuralNode outNode;
    public double weight;
    private static final double gamma = 0.005;
    private static double error = 0.000001;
    public Weight(NeuralNode i, NeuralNode o, double weight) {
        inNode = i;
        outNode = o;
        this.weight = weight;
    }

    public void updateWeight() {
        weight = weight + gamma * inNode.getValue() * outNode.getDelta();
    }
}
