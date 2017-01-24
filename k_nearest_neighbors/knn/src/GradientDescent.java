import java.util.List;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.error;

/**
 * Created by Khiem on 1/23/2017.
 */
public class GradientDescent {
    List<Vector> samples;
    int dim;
    static double gamma = 0.01;
    static double margin = 0.00001;

    public GradientDescent(List<Vector> s, int d) {
        samples = s;
        dim = d;
    }
    public Vector normal() {
        Vector delta;
        Vector weight = new Vector(dim);
        weight.fill(1);
        double output;
        do {
            delta = new Vector(dim);
            for (Vector v :
                    samples) {

                output = weight.dot(v);
                for (int i = 0; i < dim; i++) {
                    delta.set(i, gamma * (v.getValue() - output) * v.get(i));
                }
            }

            for (int i = 0; i < dim; i++) {
                weight.set(i, weight.get(i) + delta.get(i));
            }

        } while (Math.abs(error) > margin);
        return weight;
    }

    public Vector stochastic() {

    }

}
