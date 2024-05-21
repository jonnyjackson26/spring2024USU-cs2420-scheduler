import java.util.*;

class LeftistHeap<T extends Comparable<T>> {

    private static class Node<T> {
        T data;
        int rank;
        Node<T> left, right;

        public Node(T data) {
            this.data = data;
            this.rank = 1;
            this.left = this.right = null;
        }
    }

    private Node<T> root;

    public LeftistHeap() {
        this.root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(T data) {
        root = merge(root, new Node<>(data));
    }

    public T viewMin() {
        return root.data;
    }

//    public void decrementHourFromMin() {
//        root.data.duration--;
//    }

    public T deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }

        T minElement = root.data;
        root = merge(root.left, root.right);

        return minElement;
    }

    private Node<T> merge(Node<T> h1, Node<T> h2) {
        if (h1 == null) {
            return h2;
        }
        if (h2 == null) {
            return h1;
        }

        if (h1.data.compareTo(h2.data) > 0) {
            // Swap h1 and h2 to ensure h1.data is smaller
            Node<T> temp = h1;
            h1 = h2;
            h2 = temp;
        }

        h1.right = merge(h1.right, h2);

        if (h1.left == null || h1.left.rank < h1.right.rank) {
            // Swap left and right to maintain leftist property
            Node<T> temp = h1.left;
            h1.left = h1.right;
            h1.right = temp;
        }

        // Update the rank after merging
        h1.rank = (h1.right == null) ? 1 : (1 + Math.min(h1.left.rank, h1.right.rank));

        return h1;
    }



    public String toString() {
        return toString(root);
    }
    private String toString(Node<T> node) {
        if (node == null) {
            return "";
        }

        String result = node.data.toString();

        String leftStr = toString(node.left);
        String rightStr = toString(node.right);

        if (!leftStr.isEmpty() || !rightStr.isEmpty()) {
            result += " (" + leftStr + ", " + rightStr + ")";
        }

        return result;
    }

}
