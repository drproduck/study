/**
 * Created by drproduck on 2/22/17.
 */
public class ParyDP {
    static Tree my_tree;
    public static void main(String[] args) {
        Tree tree = new Tree(new Node(5));
        my_tree = tree;
        conv(tree.root);
        System.out.println(Math.max(tree.root.on, tree.root.off));
    }

    static void conv(Node t) {
        Node child = t.left_child;
        while (child != null) {
            conv(child);
            child = child.right_sibling;
        }
        if (t.on == -1) {
            if (t.isLeaf()) {
                t.on = t.value;
            } else {
                t.on = t.value;
                child = t.left_child;
                while (child != null) {
                    t.on += child.off;
                    child = child.right_sibling;
                }
            }
        }
        if (t.off == -1) {
            if (t.isLeaf()) {
                t.off = 0;
            } else {
                t.off += Math.max(t.on, t.off);
            }
        }
    }



}
class Node {
        int on;
        int off;
        int value;
        Node parent;
        Node left_child;
        Node right_sibling;

        Node(double value) {
            on = -1;
            off = -1;
        }

        boolean isLeaf() {
            return (left_child == null);
        }
}

class Tree{
    Node root;

        Tree(Node root) {
            this.root = root;
        }

        void setChildren(Node parent, Node...args) {
            Node left = args[0];
            left.parent = parent;
            parent.left_child = left;
            for (int i = 1; i < args.length; i++) {
                Node curr = args[i];
                left.right_sibling = curr;
                curr.parent = parent;
                left = curr;
            }
        }
    }
