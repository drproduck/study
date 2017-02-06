import java.util.List;

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

    public void propagate(int c) {
        int count = 0;
        while (count < c) {
            for (Vector ex :
                    examples) {
                network.initializeInputAndOutput(ex);
                for (int i = 1; i < network.getNumberOfLayers(); i++) {
                    network.updateValuesAndInputsForLayer(i);
                }

                for (int i = network.getNumberOfLayers() - 1; i >= 0; i--) {
                    network.updateDeltasForLayer(i);
                }
                network.updateWeights();
            }
            count++;
        }
    }

    public static void main(String[] args) throws Exception {
        NeuralNetwork nw = NeuralNetwork.makeCompleteNetwork(3, 2, 2, 1);
        Vector[] exs = new Vector[4];
        exs[0] = new Vector(new Vector(1), 0, 1);
        exs[1] = new Vector(new Vector(1), 1, 0);
        exs[2] = new Vector(new Vector(0), 0, 0);
        exs[3] = new Vector(new Vector(0), 1, 1);
        System.out.println(nw.net.length);
        for (List l :
                nw.net) {
            System.out.println(l);
        }
        System.out.println(nw.getWeights());
        System.out.println();
        BackPropagation bp = new BackPropagation(nw, exs);
        bp.propagate(100);
        System.out.println(nw.getWeights());
        System.out.println("testing");
        System.out.println(nw.solve(new Vector(1, 0)));
        System.out.println(nw.solve(new Vector(1, 1)));
        System.out.println(nw.solve(new Vector(0, 0)));
        System.out.println(nw.solve(new Vector(0, 1)));
    }
}
