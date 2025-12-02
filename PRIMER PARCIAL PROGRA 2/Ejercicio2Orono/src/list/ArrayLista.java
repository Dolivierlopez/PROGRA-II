package list;


public class ArrayLista<E> implements Lista<E> {
    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayLista() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    // Método para expandir el arreglo si se llena
    private void resize() {
        int newCapacity = elements.length * 2;
        Object[] newElements = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    @Override
    public void add(E element) {
        if (size == elements.length) {
            resize();
        }
        elements[size++] = element;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index >= size || index < 0) {

            return null;
        }
        return (E) elements[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        String s = "[";
        for(int i = 0; i < size; i++) {
            s += elements[i];
            if (i < size - 1) s += ", ";
        }
        s += "]";
        return s;
    }
}
