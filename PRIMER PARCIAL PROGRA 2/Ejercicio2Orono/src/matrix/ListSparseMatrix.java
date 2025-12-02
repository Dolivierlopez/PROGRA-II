package matrix;

import dictionary.ArrayMap;
import dictionary.Entry;
import dictionary.Map;
import list.ArrayLista;
import list.Lista;

public class ListSparseMatrix<E> implements SparseMatrix<E> {

    private Map<Position, E> matrix;

    public ListSparseMatrix() {
        matrix = new ArrayMap<>();
    }

    @Override
    public E put(int i, int j, E elem) {
        return matrix.put(new Position(i, j), elem);
    }

    @Override
    public E get(int i, int j) {
        return matrix.get(new Position(i, j));
    }

    @Override
    public E remove(int i, int j) {
        return matrix.remove(new Position(i, j));
    }

    @Override
    public int size() {
        return matrix.size();
    }

    @Override
    public MatrixEntry<E>[] values() {
        @SuppressWarnings("unchecked")
        MatrixEntry<E>[] result = (MatrixEntry<E>[]) new MatrixEntry[matrix.size()];
        Lista<Entry<Position, E>> entries = matrix.entries();

        for (int i = 0; i < entries.size(); i++) {
            Entry<Position, E> entry = entries.get(i);
            Position pos = entry.getKey();
            result[i] = new MatrixEntry<>(pos.getRow(), pos.getCol(), entry.getValue());
        }
        return result;
    }

    @Override
    public SparseMatrix<E> adds(SparseMatrix<E> s) {
        SparseMatrix<E> result = new ListSparseMatrix<>();
        for (MatrixEntry<E> entry : s.values()) {
            result.put(entry.getRow(), entry.getCol(), entry.getValue());
        }
        for (MatrixEntry<E> entry : this.values()) {
            result.put(entry.getRow(), entry.getCol(), entry.getValue());
        }
        return result;
    }

    @Override
    public SparseMatrix<E> transpose() {
        SparseMatrix<E> transposed = new ListSparseMatrix<>();
        for (MatrixEntry<E> entry : this.values()) {
            transposed.put(entry.getCol(), entry.getRow(), entry.getValue());
        }
        return transposed;
    }

    @Override
    public Map<Integer, Lista<E>> aDiccionario() {
        Map<Integer, Lista<E>> dictPorFilas = new ArrayMap<>();
        MatrixEntry<E>[] allValues = this.values();


        for (int i = 0; i < allValues.length; i++) {
            MatrixEntry<E> entry = allValues[i];
            int fila = entry.getRow();
            E valor = entry.getValue();

            Lista<E> listaDeFila = dictPorFilas.get(fila);

            if (listaDeFila == null) {
                listaDeFila = new ArrayLista<>();
                dictPorFilas.put(fila, listaDeFila);
            }
            listaDeFila.add(valor);
        }
        return dictPorFilas;
    }
}