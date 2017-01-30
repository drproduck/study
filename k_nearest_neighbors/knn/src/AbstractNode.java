import java.util.List;

/**
 * Created by drproduck on 1/29/17.
 */
public abstract class AbstractNode {
    protected double value;
    public List<Weight> inWeight;
    public List<Weight> outWeight;
    protected double delta;
    protected double input;
    Function f;

    protected double updateInput(){
        return input = f.squash(value);
    }

    protected void updateDelta(){
        delta = input * (1 - input) * getValue();
    }

    public double getDelta() {
        return delta;
    }

    public double getValue() {
        return value;
    }
}
