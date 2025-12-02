package tda.implementaciones;

import tda.interfaces.ListaTDA;
import tda.nodos.NodoSimple;

public class ListaDinamica<E> implements ListaTDA<E> {

    private NodoSimple<E> primero;
    private NodoSimple<E> ultimo;
    private int contador;

    public ListaDinamica() {
        this.primero = null;
        this.ultimo = null;
        this.contador = 0;
    }

    @Override
    public void agregar(E x) { // Agrega al final
        NodoSimple<E> nuevo = new NodoSimple<>(x, null);
        if (this.ultimo != null) {
            this.ultimo.setSiguiente(nuevo);
        }
        this.ultimo = nuevo;
        if (this.primero == null) {
            this.primero = this.ultimo;
        }
        this.contador++;
    }

    @Override
    public E obtener(int pos) {
        if (pos < 0 || pos >= this.contador) {
            System.err.println("Error: Posicion fuera de rango.");
            return null;
        }
        NodoSimple<E> actual = this.primero;
        for (int i = 0; i < pos; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getInfo();
    }

    @Override
    public int tamanio() { return this.contador; }
    @Override
    public boolean estaVacia() { return this.contador == 0; }
}