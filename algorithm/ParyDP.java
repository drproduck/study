/**
 * Created by drproduck on 2/22/17.
 */
public class ParyDP {
    static Tree my_tree;
    public static void main(String[] args) {
        Tree tree = new Tree();
        my_tree = tree;

    }

    static void conv(Node t) {

    }

    class Tree{
    private Node root;


}
class Node {
        int on;
        int off;
        int value;
        Node parent;
        Node left_child;
        Node right_sibling;

        Node() {
            on = -1;
            off = -1;
        }

        boolean isLeaf() {
            return (left_child == null);
        }
}
}


