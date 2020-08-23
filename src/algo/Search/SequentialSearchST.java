package algo.Search;

/**
 * 顺序查找表（基于无序链表）
 * @author 李高丞
 * @version 1.0
 */
public class SequentialSearchST<K, V> {

    private Node first;

    public V get(K key) {
        for (Node node = first; node != null; node = node.next)
            if (key.equals(node.key)) return node.value;

        return null;
    }

    public void put(K key, V value) {
        for (Node node = first; node != null; node = node.next)
            if (key.equals(node.key)) {
                node.value = value;
                return;
            }
        first = new Node(key, value, first);
    }

    private class Node {
        K key;
        V value;
        Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
