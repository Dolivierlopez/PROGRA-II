package stack;

import list.MyException;

public interface Stack<E> {
    void push(E element);
    E pop() throws MyException;
    E top() throws MyException;
    boolean isEmpty();
    int size();
}