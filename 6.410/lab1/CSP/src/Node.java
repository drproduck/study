import javafx.scene.shape.Arc;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Khiem on 1/9/2017.
 */
public class Node {
    Set<Arc> arcs;
    Set<String> domain;
    private String label;

    public Node(String l) {
        label = l;
    }

    public Node(String l, Arc... args) {
        this(l);
        arcs = new LinkedHashSet<>();
        for (Arc arg : args) {
            arcs.
        }
    }

}
