import java.util.List;

/**
 * Created by drproduck on 1/29/17.
 */
public abstract class AbstractNode {
    protected double value;
    private List<Weight> inWeight;
    private List<Weight> outWeight;
    protected double delta;
    protected double input;
    Function f;

    public double getInput(){
        return input = f.squash(value);
    }

    public void setDelta(){
        delta = input * (1 - input) * getValue();
    }

    public double getDelta() {
        return delta;
    }

    public double getValue() {
        return value;
    }
}
