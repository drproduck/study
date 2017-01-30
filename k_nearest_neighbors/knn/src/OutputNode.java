/**
 * Created by drproduck on 1/29/17.
 */
public class OutputNode extends AbstractNode{
    double expectedOutput;
    public OutputNode(){}

    public OutputNode(double expectedOutput) {
        expectedOutput = expectedOutput;
    }
    public double getDelta(){
        return delta  = input * (1 - input) * (expectedOutput - value);
    }
}
