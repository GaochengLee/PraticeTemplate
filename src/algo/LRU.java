package algo;

import java.util.*;

/**
 * @author 李高丞
 * @version 1.0
 */
public class LRU {
    private HashMap<Integer, Node> map;
    private LinkedList<Node> cache;
    private int cap;

    public LRU(int capacity) {
        this.cap = capacity;
        map = new HashMap<>();
        cache = new LinkedList<>();
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        int value = map.get(key).value;
        put(key, value);
        return value;
    }

    public void put(int key, int value) {
        Node node = new Node(key, value);
        if (map.containsKey(key)) {
            cache.remove(map.get(key));
        } else {
            if (cap == cache.size()) {
                Node last = cache.removeLast();
                map.remove(last.key);
            }
        }
        cache.addFirst(node);
        map.put(key, node);
    }
    private static class Node {
        public int key, value;
        public Node next, prev;
        public Node (int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
