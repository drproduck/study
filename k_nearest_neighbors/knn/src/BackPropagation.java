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
        for (Vector ex :
                examples) {
            network.initializeInputNodes(ex);
            for (int i = 1; i < network.getNumberOfLayers(); i++) {
                network.setValuesForLayer(i);
            }
            for (AbstractNode node :
                    network.getLayer(network.getNumberOfLayers() - 1)) {
                node.setDelta();
            }
            for (int i = network.getNumberOfLayers()-1; i >= 0 ; i--) {
                network.setDeltasForLayer(i);
            }
            //TODO: implement methods to set weights for each node, need gamma learning rate
        }
    }
}
