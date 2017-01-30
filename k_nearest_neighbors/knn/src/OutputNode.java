/**
 * Created by drproduck on 1/29/17.
 */
public class OutputNode extends NeuralNode{
    double expectedOutput;
    double value;
    double delta;
    double input;
    public double getDelta(){
        return delta = input * (1 - input) * (expectedOutput - value);
    }
}
