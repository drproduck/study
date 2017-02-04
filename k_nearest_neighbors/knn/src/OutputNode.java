import java.util.ArrayList;

/**
 * Created by drproduck on 1/29/17.
 */
public class OutputNode extends Node {
    double expectedOutput;
    public OutputNode(){
        inWeight = new ArrayList<>();
        f = Function.Sigmoid;
    }


    public OutputNode(double expectedOutput) {
        expectedOutput = expectedOutput;
    }
    public double getDelta(){
        return delta  = input * (1 - input) * (expectedOutput - value);
    }
}
