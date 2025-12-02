package dictionary;

public interface Entry<K, V> {
    K getKey();
    V getValue();
}