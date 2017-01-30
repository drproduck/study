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

    public void setValuesForLayer(int layer) {
        for (AbstractNode node :
                net[layer]) {
            NeuralNode nnode = (NeuralNode) node;
            nnode.setInput();
            nnode.setValue();
        }
    }
    public void setDeltasForLayer(int layer){
        for (AbstractNode node : net[layer]) {
            NeuralNode nnode = (NeuralNode) node;
            nnode.setDelta();
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
