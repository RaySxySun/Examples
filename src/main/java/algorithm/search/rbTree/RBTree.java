package algorithm.search.rbTree;

// https://www.cnblogs.com/skywang12345/p/3624343.html

import com.sun.org.apache.regexp.internal.RE;

/*
 * RBTree: like a binary tree. Max Height for N nodes tree is 2log(n + 1)
 * 1. every node has a color [red/black];
 * 2. root node should be black
 * 3. left notes should be black;
 * 4. if a node is red, its children nodes should be black
 * 5. the paths to any node's children nodes should have the same number of black nodes
 *
 * >> Max Height: 2log(n+1)
 * >> O(logn) for search, insert, remove
 */
public class RBTree <T extends Comparable<T>> {

    private RBTNode<T> mRoot;
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    private class RBTNode<T> {
        boolean color;
        T key;
        RBTNode<T> left;
        RBTNode<T> right;
        RBTNode<T> parent;

        public RBTNode(T key, boolean color, RBTNode<T> parent, RBTNode<T> left, RBTNode<T> right) {
            this.color = color;
            this.key = key;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    /*
     * Common Methods
     */

    private boolean isRed(RBTNode<T> node) {
        return ((node != null) && (node.color == RED))? true: false;
    }

    private boolean colorOf(RBTNode<T> node) {
        return node != null ? node.color: BLACK;
    }

    private boolean isBlack(RBTNode<T> node) {
        return !isRed(node);
    }

    private RBTNode<T> parentOf(RBTNode<T> node) {
        return node!= null ? node.parent: null;
    }

    private void setBlack(RBTNode<T> node) {
        if (node != null) {
            node.color = BLACK;
        }
    }

    private void setRed(RBTNode<T> node) {
        if (node != null) {
            node.color = RED;
        }
    }

    private void setParent(RBTNode<T> node, RBTNode<T> parent) {
        if (node != null) {
            node.parent = parent;
        }
    }

    private void setColor(RBTNode<T> node, boolean color) {
        if (node != null) {
            node.color = color;
        }
    }


/*
 *      px                              px
 *     /                               /
 *    x                               y
 *   /  \      --(left rotate)-->    / \
 *  lx   y                          x  ry
 *     /   \                       /  \
 *    ly   ry                     lx  ly
 *
 *
 */

    private void leftRotate(RBTNode<T> x) {
        RBTNode<T> y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        y.parent = x.parent;

        if (x.parent == null) {
            this.mRoot = y;
        } else {
            if (x.parent.left == x) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        }
        y.left = x;
        x.parent = y;
    }

/*
 *            py                               py
 *           /                                /
 *          y                                x
 *         /  \      --(right rotate)-->    /  \
 *        x   ry                           lx   y
 *       / \                                   / \
 *      lx  rx                                rx  ry
 *
 */

    private void rightRotate(RBTNode<T> y) {
        RBTNode<T> x = y.left;
        y.left = x.right;
        if (x.right != null) {
            y.left.parent = y;
        }
        x.parent = y.parent;
        if (y.parent == null) {
            this.mRoot = x;
        } else {
            if (y == y.parent.right) {
                y.parent.right = x;
            } else {
                y.parent.left = x;
            }
        }
        x.right = y;
        y.parent = x;
    }

    private void insert(RBTNode<T> node) {
        int cmp;
        RBTNode<T> y = null;
        RBTNode<T> x = this.mRoot;

        while (x != null) {
            y = x;
            cmp = node.key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        node.parent = y;
        if (y != null) {
            cmp = node.key.compareTo(y.key);
            if (cmp < 0) {
                y.left = node;
            } else {
                y.right = node;
            }
        } else {
            this.mRoot = node;
        }

        node.color = RED;
        insertFixUp(node);
    }

    public void instert(T key) {
        RBTNode<T> node = new RBTNode<T>(key, BLACK, null, null, null);
        if (node != null) {
            insert(node);
        }
    }

    public void insertFixUp(RBTNode<T> node) {
        RBTNode<T> parent, gparent;

        while (((parent = parentOf(node)) != null) && isRed(parent)) {
            gparent = parentOf(parent);

            if (parent == gparent.left) {
                RBTNode<T> uncle = gparent.right;

                // case 1: uncle node is red
                if ((uncle != null) && isRed(uncle)) {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    node = gparent;
                    continue;
                }

                // case 2: uncle node is black
                //         the current node is right child
                if (parent.right == node) {
                    RBTNode<T> tmp;
                    leftRotate(parent);
                    tmp = parent;
                    parent = node;
                    node = tmp;
                }

                //case 3: uncle node is black and
                //        the current node is left child
                setBlack(parent);
                setRed(gparent);
                rightRotate(gparent);
            } else {
                // if the current node's parent is
                // gparent's right child
                RBTNode<T> uncle = gparent.left;

                //case 1: uncle node is red
                if ((uncle != null) && isRed(uncle)) {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    node = gparent;
                    continue;
                }

                // case 2: uncle is black
                //         the current node is left child
                if (parent.left == node) {
                    RBTNode tmp;
                    rightRotate(parent);
                    tmp = parent;
                    parent = node;
                    node = tmp;
                }

                // case 3: uncle is black
                //         the current node is right child
                setBlack(parent);
                setRed(gparent);
                leftRotate(gparent);
            }
        }
        setBlack(this.mRoot);
    }
}
