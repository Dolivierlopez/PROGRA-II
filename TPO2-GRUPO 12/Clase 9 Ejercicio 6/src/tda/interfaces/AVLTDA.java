package tda.interfaces;

public interface AVLTDA<E extends Comparable<E>> {


    void insertar(E x);


    ListaTDA<E> obtenerElementosOrdenados();



    ListaTDA<E> recorridoPorNiveles();

    boolean estaVacio();
}