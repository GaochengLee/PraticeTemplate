package algo.Search;

/**
 * @author 李高丞
 * @version 1.0
 */
public class BST<K extends Comparable<K>, V> {
    private Node root;

    public int size() {
        return size(root);
    }

    public V get(K key) {
        return get(root, key);
    }

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    public K min() {
        return min(root).key;
    }

    public K floor(K key) {
        Node node = floor(root, key);
        if (node == null) return null;
        return node.key;
    }

    public K select(int k) {
        return select(root, k).key;
    }

    public int rank(K key) {
        return rank(key, root);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    private Node min(Node node) {
        if (node.left == null) return node;
        return min(node.left);
    }

    private Node floor(Node node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);

        if (cmp == 0) return node;
        if (cmp < 0) return floor(node.left, key);
        Node t = floor(node.right, key);
        if (t != null) return t;
        else return node;
    }

    private Node select(Node node, int k) {
        if (node == null) return null;
        int t = size(node.left);
        if (t > k) return select(node.left, k);
        else if (t < k) return select(node.right, k);
        else return node;
    }

    private int rank(K key, Node node) {
        if (node == null) return 0;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return rank(key, node.left);
        else if (cmp > 0) return 1 + size(node.left) + rank(key, node.right);
        else return size(node.left);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    private Node delete(Node node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = delete(node.left, key);
        else if (cmp > 0) node.right = delete(node.right, key);
        else {
            if (node.right == null) return node.left;
            if (node.left == null) return node.right;
            Node t = node;
            node = min(node.right);
            node.right = deleteMin(t.right);
            node.left = t.left;
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    private int size(Node node) {
        if (node == null) return 0;
        else return node.N;
    }

    private V get(Node node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return get(node.left, key);
        else if (cmp > 0) return get(node.right, key);
        else return node.value;
    }

    private Node put(Node node, K key, V value) {
        if (node == null) return new Node(key, value, 1);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, value);
        else if (cmp > 0) node.right = put(node.right, key, value);
        else node.value = value;

        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    private class Node {
        private K key;
        private V value;
        private Node left, right;
        private int N;

        public Node(K key, V value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }
}
