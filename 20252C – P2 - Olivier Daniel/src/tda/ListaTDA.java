package tda;

// TDA Lista (mínimo necesario para el parcial)
public interface ListaTDA<E> {
    void InicializarLista();
    void add(E x); // Agregar (usaremos agregar al inicio por simplicidad)
    int size();
    E get(int index);
    boolean isEmpty();
    void remove(E x); // Necesario para el MapaTDA
}