/**
 * Created by Khiem on 1/19/2017.
 */
public class NDPoint {
    double[] co;
    int dim;
    int value;

    public NDPoint(double... args) {
        dim = args.length;
        for (int i = 0; i < dim; i++) {
            co[i] = args[i];
        }
    }

    public int setValue(int v) {
        value = v;
    }

    public double distTo(NDPoint other) {

        double sum = 0;
        for (int i = 0; i < dim; i++) {
            sum += Math.pow(co[i] - other.co[i], 2);
        }
        return Math.sqrt(sum);
    }

}
