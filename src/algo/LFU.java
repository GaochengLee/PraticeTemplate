package algo;

import java.util.HashMap;

/**
 * @author 李高丞
 * @version 1.0
 */
public class LFU<K, V> {
    HashMap<K, Node<K, V>> cache;
    HashMap<Integer, NodeLinkedList<K, V>> freqMap;
    int capacity;
    int min;

    public LFU (int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity);
        this.freqMap = new HashMap<>();
    }

    public V get(K key) {
        Node<K, V> node = cache.get(key);
        if (node == null) return null;
        freqInc(node);
        return node.value;
    }

    public void put(K key, V value) {
        if (capacity == 0) return;
        Node<K, V> node = cache.get(key);
        if (node != null) {
            node.value = value;
            freqInc(node);
        } else {
            if (cache.size() == capacity) {
                NodeLinkedList<K, V> list = freqMap.get(min);
                cache.remove(list.tail.pre.key);
                list.removeNode(list.tail.pre);
            }
            Node<K, V> newNode = new Node<>(key, value);
            cache.put(key, newNode);
            NodeLinkedList<K, V> list = freqMap.get(1);
            if (list == null) {
                list = new NodeLinkedList<>();
                freqMap.put(1, list);
            }
            list.addNode(newNode);
            min = 1;
        }
    }

    void freqInc(Node<K, V> node) {
        int freq = node.freq;
        NodeLinkedList<K, V> linkedList = freqMap.get(freq);
        linkedList.removeNode(node);

        if (freq == min && linkedList.head.next == linkedList.tail) {
            min = freq + 1;
        }

        node.freq++;
        linkedList = freqMap.get(freq + 1);
        if (linkedList == null) {
            linkedList = new NodeLinkedList<>();
            freqMap.put(freq + 1, linkedList);
        }
        linkedList.addNode(node);
    }

    private static class Node<K, V> {
        K key;
        V value;
        int freq = 1;
        Node<K, V> pre, next;
        public Node() {}
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private static class NodeLinkedList<K, V> {
        Node<K, V> head, tail;

        public NodeLinkedList() {
            head = new Node<>();
            tail = new Node<>();
            head.next = tail;
            tail.pre = head;
        }

        void removeNode(Node<K, V> node) {
            node.next.pre = node.pre;
            node.pre.next = node.next;
        }

        void addNode(Node<K, V> node) {
            node.next = head.next;
            node.pre = head;
            head.next.pre = node;
            head.next = node;
        }
    }
}
