package tda.implementaciones;

import tda.interfaces.ColaTDA;
import tda.nodos.NodoSimple;

public class ColaDinamica<E> implements ColaTDA<E> {

    private NodoSimple<E> primero;
    private NodoSimple<E> ultimo;
    private int contador;

    public ColaDinamica() {
        this.primero = null;
        this.ultimo = null;
        this.contador = 0;
    }

    @Override
    public void acolar(E x) {
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
    public void desacolar() {
        if (this.primero == null) {
            System.err.println("Error: La cola esta vacia.");
            return;
        }
        this.primero = this.primero.getSiguiente();
        if (this.primero == null) {
            this.ultimo = null; // La cola quedó vacía
        }
        this.contador--;
    }

    @Override
    public E primero() {
        if (this.primero == null) {
            return null; // O lanzar una excepción
        }
        return this.primero.getInfo();
    }

    @Override
    public boolean colaVacia() {
        return this.primero == null;
    }

    @Override
    public int tamanio() {
        return this.contador;
    }
}