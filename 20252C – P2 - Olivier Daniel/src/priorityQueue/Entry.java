package priorityQueue;

public interface Entry<K extends Comparable<K>,V> extends Comparable<Entry<K,V>>  { 
	public K getKey(); 
	public V getValue(); 
}

