import java.util.Arrays;

/**
 * Created by Khiem on 1/19/2017.
 */
public class Vector {
    double[] co;
    int dim;
    private int value;
    private boolean testable = false;

    public int getValue() {
        return value;
    }

    public void setValue(int v) {
        value = v;
    }

    public double get(int t) {
        return co[t];
    }

    public Vector(int value, double... args) {
        dim = args.length;
        for (int i = 0; i < dim; i++) {
            co[i] = args[i];
        }
        this.value = value;
    }

    public Vector(int d) {
        dim = d;
        co = new double[d];
    }

    public Vector(int d, boolean b) {
        this(d);
        testable = b;
    }

    public void fill(double i) {
        for (int j = 0; j < dim; j++) {
            co[j] = i;
        }
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

    public void random(int valueFrom, int valueTo) throws Exception{
        if (!testable) {
            throw new UnsupportedOperationException("Not allowed to be tested");
        }
        else {
            for (int i = 0; i < dim; i++) {
                co[i] = 100 * Math.random();
            }
            value = valueFrom + (int) ((valueTo - valueFrom) * Math.random());
            System.out.printf("randomize for testing: value: %d, coordinates: %s\n", value, Arrays.toString(co));
        }
    }
}
