package tda.nodos;

public class NodoAVL<E extends Comparable<E>> {
    private E info;
    private NodoAVL<E> izquierdo;
    private NodoAVL<E> derecho;
    private int altura; // Clave para el balanceo AVL

    public NodoAVL(E info) {
        this.info = info;
        this.izquierdo = null;
        this.derecho = null;
        this.altura = 1; // Un nodo nuevo es una hoja, altura 1
    }

    // --- Getters y Setters ---

    public E getInfo() {
        return info;
    }

    public void setInfo(E info) {
        this.info = info;
    }

    public NodoAVL<E> getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoAVL<E> izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoAVL<E> getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoAVL<E> derecho) {
        this.derecho = derecho;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
}