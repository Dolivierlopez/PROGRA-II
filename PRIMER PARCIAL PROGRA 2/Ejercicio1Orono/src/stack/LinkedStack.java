package stack;

import list.MyException;
import list.Node;

public class LinkedStack<E> implements Stack<E> {
    private Node<E> top;
    private int size;

    public LinkedStack() {
        top = null;
        size = 0;
    }

    @Override
    public void push(E element) {
        Node<E> newNode = new Node<>(element, top);
        top = newNode;
        size++;
    }

    @Override
    public E pop() throws MyException {
        if (isEmpty()) {
            throw new MyException("La pila está vacía, no puedo desapilar un elemento");
        }
        Node<E> oldTop = top;
        top = oldTop.getNext();
        size--;
        return oldTop.getElement();
    }

    @Override
    public E top() throws MyException {
        if (isEmpty()) {
            throw new MyException("No se puede ver el primer elemento porque la pila está vacía");
        }
        return top.getElement();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }
}