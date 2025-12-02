package tda.implementaciones;

import tda.interfaces.AVLTDA;
import tda.interfaces.ColaTDA;
import tda.interfaces.ListaTDA;
import tda.nodos.NodoAVL;

public class AVL<E extends Comparable<E>> implements AVLTDA<E> {

    private NodoAVL<E> raiz;

    public AVL() {
        this.raiz = null;
    }

    @Override
    public boolean estaVacio() {
        return this.raiz == null;
    }

    // --- Métodos de Inserción (Público y Privado Recursivo) ---

    @Override
    public void insertar(E x) {
        this.raiz = insertarRecursivo(this.raiz, x);
    }


    private NodoAVL<E> insertarRecursivo(NodoAVL<E> nodo, E x) {
        // 1. Inserción estándar de ABB
        if (nodo == null) {
            return new NodoAVL<>(x);
        }

        int comparacion = x.compareTo(nodo.getInfo());

        if (comparacion < 0) {
            nodo.setIzquierdo(insertarRecursivo(nodo.getIzquierdo(), x));
        } else if (comparacion > 0) {
            nodo.setDerecho(insertarRecursivo(nodo.getDerecho(), x));
        } else {
            // Elemento duplicado, no se inserta
            return nodo;
        }

        // 2. Actualizar altura del nodo actual
        actualizarAltura(nodo);

        // 3. Obtener Factor de Balanceo (FB)
        int fb = factorBalance(nodo);

        // 4. Aplicar Rotaciones si está desbalanceado

        // Caso Izquierda-Izquierda (LL) [cite: 692]
        if (fb > 1 && x.compareTo(nodo.getIzquierdo().getInfo()) < 0) {
            return rotacionDerecha(nodo);
        }

        // Caso Derecha-Derecha (RR) [cite: 694]
        if (fb < -1 && x.compareTo(nodo.getDerecho().getInfo()) > 0) {
            return rotacionIzquierda(nodo);
        }

        // Caso Izquierda-Derecha (LR) [cite: 693]
        if (fb > 1 && x.compareTo(nodo.getIzquierdo().getInfo()) > 0) {
            nodo.setIzquierdo(rotacionIzquierda(nodo.getIzquierdo()));
            return rotacionDerecha(nodo);
        }

        // Caso Derecha-Izquierda (RL) [cite: 695]
        if (fb < -1 && x.compareTo(nodo.getDerecho().getInfo()) < 0) {
            nodo.setDerecho(rotacionDerecha(nodo.getDerecho()));
            return rotacionIzquierda(nodo);
        }

        // 5. Devolver el nodo (balanceado o no era necesario)
        return nodo;
    }

    // --- Métodos de Recorrido (Para las salidas) ---

    @Override
    public ListaTDA<E> obtenerElementosOrdenados() {
        ListaTDA<E> lista = new ListaDinamica<>();
        recorridoInOrder(this.raiz, lista);
        return lista;
    }


    private void recorridoInOrder(NodoAVL<E> nodo, ListaTDA<E> lista) {
        if (nodo != null) {
            recorridoInOrder(nodo.getIzquierdo(), lista);
            lista.agregar(nodo.getInfo());
            recorridoInOrder(nodo.getDerecho(), lista);
        }
    }

    @Override
    public ListaTDA<E> recorridoPorNiveles() {
        ListaTDA<E> resultado = new ListaDinamica<>();
        if (this.raiz == null) {
            return resultado;
        }

        // Usamos nuestra TDA Cola implementada desde cero
        ColaTDA<NodoAVL<E>> cola = new ColaDinamica<>();
        cola.acolar(this.raiz);

        while (!cola.colaVacia()) {
            NodoAVL<E> actual = cola.primero();
            cola.desacolar();

            resultado.agregar(actual.getInfo());

            if (actual.getIzquierdo() != null) {
                cola.acolar(actual.getIzquierdo());
            }
            if (actual.getDerecho() != null) {
                cola.acolar(actual.getDerecho());
            }
        }
        return resultado;
    }


    // --- Métodos auxiliares de AVL (Altura, FB, Rotaciones) ---

    private int altura(NodoAVL<E> nodo) {
        if (nodo == null) {
            return 0; // Altura de un nodo nulo es 0
        }
        return nodo.getAltura();
    }

    private void actualizarAltura(NodoAVL<E> nodo) {
        if (nodo != null) {
            nodo.setAltura(1 + max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho())));
        }
    }

    private int max(int a, int b) {
        return (a > b) ? a : b;
    }


    private int factorBalance(NodoAVL<E> nodo) {
        if (nodo == null) {
            return 0;
        }
        return altura(nodo.getIzquierdo()) - altura(nodo.getDerecho());
    }


    private NodoAVL<E> rotacionDerecha(NodoAVL<E> y) {
        NodoAVL<E> x = y.getIzquierdo();
        NodoAVL<E> T2 = x.getDerecho();

        // Realizar rotacion
        x.setDerecho(y);
        y.setIzquierdo(T2);

        // Actualizar alturas (Importante: primero 'y', luego 'x')
        actualizarAltura(y);
        actualizarAltura(x);

        // Devolver nueva raiz del subarbol
        return x;
    }

    private NodoAVL<E> rotacionIzquierda(NodoAVL<E> x) {
        NodoAVL<E> y = x.getDerecho();
        NodoAVL<E> T2 = y.getIzquierdo();

        // Realizar rotacion
        y.setIzquierdo(x);
        x.setDerecho(T2);

        // Actualizar alturas (Importante: primero 'x', luego 'y')
        actualizarAltura(x);
        actualizarAltura(y);

        // Devolver nueva raiz del subarbol
        return y;
    }
}