import java.util.Arrays;

/**
 * Created by Khiem on 1/19/2017.
 */
public class Vector {
    double[] co;
    int dim;
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int v) {
        value = v;
    }

    public double get(int t) {
        return co[t];
    }

    public Vector(double... args) {
        dim = args.length;
        for (int i = 0; i < dim; i++) {
            co[i] = args[i];
        }
    }

    public Vector(int d) {
        dim = d;
        co = new double[d];
    }

    public void fill(double i) {
        Arrays.fill(co, i);
    }

    public void set(int i, double j) {
        co[i] = j;
    }

    public double distTo(Vector other) {

        double sum = 0;
        for (int i = 0; i < dim; i++) {
            sum += Math.pow(co[i] - other.co[i], 2);
        }
        return Math.sqrt(sum);
    }

    public double dot(Vector other) {
        double p = 1;
        for (int i = 0; i < dim; i++) {
            p += co[0] * other.co[0];
        }
        return p;
    }
}
