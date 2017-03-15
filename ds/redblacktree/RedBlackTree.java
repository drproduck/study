import java.util.TreeMap;

/**
 * Created by drproduck on 2/11/17.
 */
public class RedBlackTree {
    Node root;
    TreeMap

    public void left_rotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != Node.NIL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == Node.NIL) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else x.parent.right = y;
        y.left = x;
        x.parent = y;
    }

    public void right_rotate(Node y) {
        Node x = y.left;
        y.left = x.right;
        if (x.right != Node.NIL) {
            x.right.parent = y;
        }
        x.parent = y.parent;
        if (y.parent == Node.NIL) {
            root = x;
        } else if (y.parent.left == y) {
            x = y.parent.left;
        } else x = y.parent.right;
        x.right = y;
        y.parent = x;
    }

    public void insert(Node z) {
        Node y = Node.NIL;
        Node x = root;
        while (x != Node.NIL) {
            y = x;
            if (z.key < x.key) {
                x = x.left;
            } else x = x.right;
        }
        z.parent = y;
        if (y == Node.NIL) {
            root = z;
        } else if (z.key < y.key) {
            y.left = z;
        } else y.right = z;
        z.left = Node.NIL;
        z.right = Node.NIL;
        z.black = false;
        insert_fix(z);
    }

    public void insert_fix(Node z) {
        while (!z.parent.black) {
            if (z.parent == z.parent.parent.left) {
                Node y = z.parent.parent.right;
                if (!y.black) {
                    z.parent.black = true;
                    y.black = true;
                    z.parent.parent.black = false;
                    z = z.parent.parent;
                } else if (z == z.parent.right) {
                    z = z.parent;
                    left_rotate(z);
                    z.parent.black = true;
                    z.parent.parent.black = false;
                    right_rotate(z.parent.parent);
                }
            } else {
                Node y = z.parent.parent.left;
                if (!y.black) {
                    z.parent.black = true;
                    y.black = true;
                    z.parent.parent.black = false;
                    z = z.parent.parent;
                } else if (z == z.parent.left) {
                    z = z.parent;
                    right_rotate(z);
                    z.parent.black = true;
                    z.parent.parent.black = false;
                    left_rotate(z.parent.parent);
                }
            }
        }
    }

    public void transplant(Node u, Node v) {
        if (u.parent == Node.NIL) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else u.parent.right = v;
        v.parent = u.parent;
    }

    public void delete(Node z) {
        Node y = z;
        boolean y_original_color = y.black;
        Node x;
        if (z.left == Node.NIL) {
            x = z.right;
            transplant(z, z.right);
        } else if (z.right == Node.NIL) {
            x = z.left;
            transplant(z, z.left);
        } else {
            y = minimum(z.right);
            y_original_color = y.black;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.black = z.black;
        }
        if (y_original_color) {
            delete_fix(x);
        }
    }

    public void delete_fix(Node x) {
        Node w;
        while (x != root && x.black) {
            if (x == x.parent.left) {
                w = x.parent.right;
                if (!w.black) {
                    w.black = true;
                    x.parent.black = false;
                    left_rotate(x.parent);
                    w = x.parent.right;
                    if (w.left.black && w.right.black) {
                        w.black = false;
                        x = x.parent;
                    } else if (w.right.black) {
                        w.left.black = true;
                        w.black = false;
                        right_rotate(w);
                        w = x.parent.right;
                    }
                    w.black = x.parent.black;
                    x.parent.black = true;
                    w.right.black = true;
                    left_rotate(x.parent);
                    x = root;
                }
            } else {
                w = x.parent.left;
                if (!w.black) {
                    w.black = true;
                    x.parent.black = false;
                    right_rotate(x.parent);
                    w = x.parent.left;
                    if (w.right.black && w.left.black) {
                        w.black = false;
                        x = x.parent;
                    } else if (w.left.black) {
                        w.right.black = true;
                        w.black = false;
                        left_rotate(w);
                        w = x.parent.left;
                    }
                    w.black = x.parent.black;
                    x.parent.black = true;
                    w.left.black = true;
                    right_rotate(x.parent);
                    x = root;
                }
            }
        }
        x.black = true;
    }

    public Node minimum(Node x) {
        while (x.left != Node.NIL) {
            x = x.left;
        }
        return x;
    }

    public Node maximum(Node x) {
        while (x.right != Node.NIL) {
            x = x.right;
        }
        return x;
    }
}

class Node {
        double key;
        Node left;
        Node right;
        Node parent;
        boolean black;

        Node (boolean b) {
            black = b;
        }
        Node(double value, boolean b) {
            this.key = value;
            black = b;
        }

        static final Node NIL = new Node(true); // NIL node is always black
    }