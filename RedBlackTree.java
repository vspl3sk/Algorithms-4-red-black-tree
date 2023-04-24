public class RedBlackTree {
    public static void main(String[] args) {

        RedBlackTree tree = new RedBlackTree();
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = ((int) (Math.random() * 100));
            System.out.print(array[i] + " ");

        }

        for (int i = 0; i < array.length; i++) {
            tree.add(array[i]);
        }
        System.out.println("\n" + "RedBlackTree");
        printNode(tree.root, " ");

    }

    private Node root;

    public boolean add(int value) {
        if (root != null) {
            boolean result = addNode(root, value);
            root = rebalance(root);
            root.color = Color.Black;
            return result;
        } else {
            root = new Node();
            root.color = Color.Black;
            root.value = value;
            return true;
        }
    }

    private class Node {

        private int value;
        private Color color;
        private Node leftChild;
        private Node rightChild;
    }

    private enum Color {
        Red, Black
    }

    private boolean addNode(Node node, int value) {
        if (node.value == value) {
            return false;
        } else {
            if (node.value > value) {
                if (node.leftChild != null) {
                    boolean result = addNode(node.leftChild, value);
                    node.leftChild = rebalance(node.leftChild);
                    return result;
                } else {
                    node.leftChild = new Node();
                    node.leftChild.color = Color.Red;
                    node.leftChild.value = value;
                    return true;
                }
            } else {
                if (node.rightChild != null) {
                    boolean result = addNode(node.rightChild, value);
                    node.rightChild = rebalance(node.rightChild);
                    return result;

                } else {
                    node.rightChild = new Node();
                    node.rightChild.color = Color.Red;
                    node.rightChild.value = value;
                    return true;
                }
            }
        }
    }

    private void colorSwap(Node node) {
        node.rightChild.color = Color.Black;
        node.leftChild.color = Color.Black;
        node.color = Color.Red;
    }

    private Node leftSwap(Node node) {
        Node leftChild = node.leftChild;
        Node betweenChild = leftChild.rightChild;
        leftChild.rightChild = node;
        node.leftChild = betweenChild;
        leftChild.color = node.color;
        node.color = Color.Red;
        return leftChild;
    }

    private Node rightSwap(Node node) {
        Node rightChild = node.rightChild;
        Node betweenChild = rightChild.leftChild;
        rightChild.leftChild = node;
        node.rightChild = betweenChild;
        rightChild.color = node.color;
        node.color = Color.Red;
        return rightChild;
    }

    private Node rebalance(Node node) {
        Node result = node;
        boolean needRebalance;
        do {
            needRebalance = false;
            if (result.rightChild != null && result.rightChild.color == Color.Red &&
                    (result.leftChild == null || result.leftChild.color == Color.Black)) {
                needRebalance = true;
                result = rightSwap(result);
            }
            if (result.leftChild != null && result.leftChild.color == Color.Red &&
                    (result.leftChild.leftChild != null && result.leftChild.leftChild.color == Color.Red)) {
                needRebalance = true;
                result = leftSwap(result);
            }
            if (result.leftChild != null && result.leftChild.color == Color.Red &&
                    result.rightChild != null && result.rightChild.color == Color.Red) {
                needRebalance = true;
                colorSwap(result);
            }
        } while (needRebalance);
        return result;
    }

    static void printNode(Node node, String sp) {
        if (node != null) {
            System.out.println(sp + node.value);
            printNode(node.leftChild, sp + "left ");
            printNode(node.rightChild, sp + "right ");
        }
    }

}