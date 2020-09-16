package interview;

public class MyHashMap<K, V> implements MyMap<K, V> {

    final static int DEFAULT_CAPACITY = 16;
    final static float DEFAULT_LOAD_FACTOR = 0.75f;

    int capacity;
    float loadFactor;
    int size = 0;

    Entry<K,V>[] table;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void clear() {
        table = null;
        size = 0;
    }

    @Override
    public void put(K k, V v) {
        int index = k.hashCode() % table.length;
        Entry<K, V> current = table[index];
        if (current != null) {
            while (current != null) {
                if (current.k == k) {
                    current.v = v;
                }
                current = current.next;
            }
            table[index] = new Entry<>(k, v, table[index]);
            // 尾插法
            // current.next = new Entry<>(k, v, null);
            size++;
        } else {
            table[index] = new Entry<>(k, v, null);
            size++;
        }
    }

    @Override
    public V get(K k) {
        int index = k.hashCode() % table.length;
        Entry<K, V> current = table[index];
        while (current != null) {
            if (current.k == k) return current.v;
            current = current.next;
        }
        return null;
    }

    public V remove(K k) {
        int index = k.hashCode() % table.length;
        Entry<K, V> cur = table[index];
        if (cur.k == k) {
            table[index] = null;
            size--;
            return cur.v;
        }

        while (cur.next != null) {
            if (cur.next.k == k) {
                V ov = cur.next.v;
                cur.next = cur.next.next;
                size--;
                return ov;
            }
            cur = cur.next;
        }

        return null;
    }

    class Entry<K, V> {
        K k;
        V v;
        Entry<K, V> next;

        public Entry(K k, V v, Entry<K, V> next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }
    }
}