import java.util.List;

/**
 * Created by drproduck on 1/29/17.
 */
public enum Function {
    Sigmoid, Discrete;

    double squash(double value){
        if (this == Sigmoid)
            return 1 / (1 + Math.exp(-1 * value));
        else if (this == Discrete)
            return (value < 0) ? -1 : 1;
        return 0;
    }


}
