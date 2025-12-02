package dictionary;
import list.Lista;

public interface Map<K, V> {
    int size();
    boolean isEmpty();
    V get(K key);
    V put(K key, V value);
    V remove(K key);

    Lista<Entry<K, V>> entries();
}