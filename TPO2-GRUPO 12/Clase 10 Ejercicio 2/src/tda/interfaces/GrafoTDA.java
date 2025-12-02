package tda.interfaces;

public interface GrafoTDA<E> {


    void agregarVertice(E v);
    void eliminarVertice(E v);
    E[] vertices(); // Devuelve un arreglo de etiquetas
    void agregarArista(E v1, E v2, int peso);
    void eliminarArista(E v1, E v2);
    boolean existeArista(E v1, E v2);
    int pesoArista(E v1, E v2);


    int mayorCostoSalientes(E v);


    ListaTDA<E> predecesores(E v);
}