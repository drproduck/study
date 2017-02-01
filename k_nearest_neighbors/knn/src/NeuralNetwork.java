import java.util.List;

/**
 * Created by Khiem on 1/26/2017.
 */
public class NeuralNetwork {
    public List<Node>[] net;

    public int getNumberOfLayers() {
        return numberOfLayers;
    }

    private int numberOfLayers;

    public NeuralNetwork(int numLayers) {
        numberOfLayers = numLayers;
    }

    public void makeCompleteNetWork(int... args) {
        for (int i = 0; i < numberOfLayers; i++) {
            makeNodesInLayer(i, args[i]);
        }
        for (int i = 0; i < numberOfLayers - 1; i++) {
            completeConnectionsBetweenLayers(i, i + 1);
        }
    }

    public static NeuralNetwork makeNetWork(int numLayers, int... args) {
        NeuralNetwork nn = new NeuralNetwork(numLayers);
        nn.makeCompleteNetWork(args);
        return nn;
    }

    public Node add(int layer) {
        //if layer is output layer
        Node node;
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

    public List<Node> getLayer(int layer) {
        return net[layer];
    }

    public void updateValuesForLayer(int layer) {
        for (Node node :
                net[layer]) {
            NeuralNode nnode = (NeuralNode) node;
            nnode.setInput();
            nnode.setValue();
        }
    }
    public void updateDeltasForLayer(int layer){
        for (Node node : net[layer]) {
            NeuralNode nnode = (NeuralNode) node;
            nnode.updateDelta();
        }
    }

    public void updateWeights() {
        for (int i = 0; i < numberOfLayers-1; i++) {
            List<Node> layer = getLayer(i);
            for (Node node :
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

    public void makeNodesInLayer(int layer, int numNodes){
        while (numNodes > 0) {
            add(layer);
            numNodes --;
            }
        }

    public void completeConnectionsBetweenLayers(int l1, int l2) {
        List<Node> layer1 = getLayer(l1);
        List<Node> layer2 = getLayer(l2);
        for (Node n1 :
                layer1) {
            for (Node n2 :
                    layer2) {
                n1.connectsTo(n2);
            }
        }
    }
}
