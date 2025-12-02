package tda.interfaces;

public interface ColaTDA<E> {
    void acolar(E x);
    void desacolar();
    E primero();
    boolean colaVacia();
    int tamanio();
}