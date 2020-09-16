package interview;

public interface MyMap<K, V> {
    void put(K k, V v);
    V get(K k);
    int size();
    V remove(K k);
    boolean isEmpty();
    void clear();
}
