package tda.nodos;

public class NodoSimple<E> {
    private E info;
    private NodoSimple<E> siguiente;

    public NodoSimple(E info, NodoSimple<E> siguiente) {
        this.info = info;
        this.siguiente = siguiente;
    }

    public E getInfo() {
        return info;
    }

    public void setInfo(E info) {
        this.info = info;
    }

    public NodoSimple<E> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoSimple<E> siguiente) {
        this.siguiente = siguiente;
    }
}