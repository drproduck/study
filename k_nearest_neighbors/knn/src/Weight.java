/**
 * Created by Khiem on 1/26/2017.
 */
public class Weight {
    public Node inNode;
    public Node outNode;
    public double weight;
    private static final double gamma = 0.05;
    public Weight(Node i, Node o) {
        inNode = i;
        outNode = o;
        this.weight = 2;
    }

    public void updateWeight() {
        weight = weight + gamma * inNode.getValue() * outNode.getDelta();
    }

    public double getWeight() {
        return weight;
    }
}

