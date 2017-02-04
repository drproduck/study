import java.util.Collection;

/**
 * Created by Khiem on 1/26/2017.
 */
public class BackPropagation {
    private NeuralNetwork network;
    private Vector[] examples;
    public BackPropagation(NeuralNetwork net, Vector[] exs) {
        network = net;
        examples = exs;
    }

    public void propagate(int error, int count) {
        while (count < 10000) {
            for (Vector ex :
                    examples) {
                network.initializeInputNodes(ex);
                for (int i = 1; i < network.getNumberOfLayers(); i++) {
                    network.updateValuesForLayer(i);
                }
                for (Node node :
                        network.getLayer(network.getNumberOfLayers() - 1)) {
                    node.updateDelta();
                }
                for (int i = network.getNumberOfLayers() - 1; i >= 0; i--) {
                    network.updateDeltasForLayer(i);
                }
                //TODO: implement methods to set weights for each node, need gamma learning rate
                network.updateWeights();
            }
            count++;
        }
    }

    public static void main(String[] args) {
        NeuralNetwork nw = NeuralNetwork.makeNetWork(3, 2 , 2, 2);
        Vector[] exs = {new Vector(1, 1, 1), new Vector(1, 1, 0), new Vector(1, 0, 0), new Vector(1, 0, 1)};
        System.out.println(nw.getWeights());
        BackPropagation bp = new BackPropagation(nw, exs);
        System.out.println(nw.getWeights());
    }
}
