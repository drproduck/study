import java.util.Set;

/**
 * Created by Khiem on 1/26/2017.
 */
public class BackPropagation {
    private NeuralNetwork network;
    private Set<Vector> examples;
    public BackPropagation(NeuralNetwork net, Set<Vector> exs) {
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
        }
    }
}
