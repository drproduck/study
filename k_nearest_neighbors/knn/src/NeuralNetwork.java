import java.util.List;

/**
 * Created by Khiem on 1/26/2017.
 */
public class NeuralNetwork {
    public List<AbstractNode>[] net;

    public int getNumberOfLayers() {
        return numberOfLayers;
    }

    private int numberOfLayers;
    public NeuralNetwork(int numLayers) {
        numberOfLayers = numLayers;
    }

    public AbstractNode add(int layer) {
        //if layer is output layer
        AbstractNode node;
        if (layer == numberOfLayers - 1) {
            node = new OutputNode();
            net[numberOfLayers - 1].add(node);
        } else if (layer == 1) {
            node = new InputNode();
            net[0].add(node);
        } else {
            node = new NeuralNode();
            net[layer].add(node);
        }
        return node;
    }

    public List<AbstractNode> getLayer(int layer) {
        return net[layer];
    }

    public void updateValuesForLayer(int layer) {
        for (AbstractNode node :
                net[layer]) {
            NeuralNode nnode = (NeuralNode) node;
            nnode.setInput();
            nnode.setValue();
        }
    }
    public void updateDeltasForLayer(int layer){
        for (AbstractNode node : net[layer]) {
            NeuralNode nnode = (NeuralNode) node;
            nnode.updateDelta();
        }
    }

    public void updateWeights() {
        for (int i = 0; i < numberOfLayers-1; i++) {
            List<AbstractNode> layer = getLayer(i);
            for (AbstractNode node :
                    layer) {
                List<Weight> weights = node.outWeight;
                for (Weight w :
                        weights) {
                    w.updateWeight();
                }
            }
        }
    }

    public void initializeInputNodes(Vector vector) {
        InputNode inode;
        for (int i = 0; i < vector.getDim(); i++) {
            inode = (InputNode) net[0].get(i);
            inode.setInput(vector.x(i));
        }
    }
}
