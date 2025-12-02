package dictionary;

import list.ArrayLista;
import list.Lista;

public class ArrayMap<K, V> implements Map<K, V> {


    private Lista<MapEntry<K, V>> list;

    public ArrayMap() {
        list = new ArrayLista<>();
    }


    private static class MapEntry<K, V> implements Entry<K, V> {
        private K key;
        private V value;
        public MapEntry(K key, V value) { this.key = key; this.value = value; }
        public K getKey() { return key; }
        public V getValue() { return value; }
        public void setValue(V value) { this.value = value; }
    }

    private int findKey(K key) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getKey().equals(key)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() { return list.size(); }

    @Override
    public boolean isEmpty() { return list.isEmpty(); }

    @Override
    public V get(K key) {
        int index = findKey(key);
        return (index != -1) ? list.get(index).getValue() : null;
    }

    @Override
    public V put(K key, V value) {
        int index = findKey(key);
        if (index != -1) {
            V oldValue = list.get(index).getValue();
            list.get(index).setValue(value);
            return oldValue;
        }
        list.add(new MapEntry<>(key, value));
        return null;
    }

    @Override
    public V remove(K key) {

        return null;
    }


    @Override
    public Lista<Entry<K, V>> entries() {

        Lista<Entry<K, V>> entriesList = new ArrayLista<>();


        for (int i = 0; i < list.size(); i++) {
            entriesList.add(list.get(i));
        }


        return entriesList;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "{ }";
        String s = "{ ";

        Lista<Entry<K, V>> entries = this.entries();
        for(int i = 0; i < entries.size(); i++) {
            Entry<K,V> entry = entries.get(i);
            s += entry.getKey() + "=" + entry.getValue();
            if (i < entries.size() - 1) s += ", ";
        }
        s += " }";
        return s;
    }
}