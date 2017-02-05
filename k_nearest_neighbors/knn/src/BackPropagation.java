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
        NeuralNetwork nw = NeuralNetwork.makeCompleteNetwork(3, 2, 3, 1);
        Vector[] exs = new Vector[1000];
        for (int i = 0; i < exs.length; i++) {
            int a = (int)(1000 * Math.random());
            int b = (int) (1000 * Math.random());
            int c= a+b;
            Vector x = new Vector(new Vector(c), a, b);
            exs[i] = x;
        }
        System.out.println(nw.net.length);
        for (List l :
                nw.net) {
            System.out.println(l);
        }
        System.out.println(nw.getWeights());
        System.out.println();
        BackPropagation bp = new BackPropagation(nw, exs);
        bp.propagate(10000);
        System.out.println(nw.getWeights());
    }
}
