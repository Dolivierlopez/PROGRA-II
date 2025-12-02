package tda.interfaces;

public interface ListaTDA<E> {
    void agregar(E x); // Agrega al final
    E obtener(int pos);
    int tamanio();
    boolean estaVacia();
}