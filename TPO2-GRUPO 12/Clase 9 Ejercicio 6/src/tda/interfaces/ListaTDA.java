package tda.interfaces;

public interface ListaTDA<E> {
    void agregar(E x);
    E obtener(int pos);
    int tamanio();
    boolean estaVacia();
}