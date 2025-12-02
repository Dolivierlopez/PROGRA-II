package list;

public interface Lista<E> {
    void add(E element);
    E get(int index);
    int size();
    boolean isEmpty();
}