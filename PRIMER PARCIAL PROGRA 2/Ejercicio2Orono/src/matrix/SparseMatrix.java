package matrix;

import dictionary.Map;
import list.Lista;

public interface SparseMatrix<E> {
    E put(int i, int j, E elem);
    E get(int i, int j);
    E remove(int i, int j);
    int size();

    MatrixEntry<E>[] values();
    SparseMatrix<E> adds(SparseMatrix<E> s);
    SparseMatrix<E> transpose();
    Map<Integer, Lista<E>> aDiccionario();
}