package tda;

/**
 * CORRECCIÓN: Versión de NodoLista con campos de acceso de paquete.
 * Esto evita el error de "Type Mismatch" en los setters genéricos.
 */
public class NodoLista<E> {

    E info; // Sin 'private' para acceso de paquete
    NodoLista<E> sig; // Sin 'private' para acceso de paquete

    // Constructor (opcional, pero buena práctica)
    public NodoLista() {
        this.info = null;
        this.sig = null;
    }
}