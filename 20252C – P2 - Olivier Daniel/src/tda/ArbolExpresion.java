package tda;

/**
 * Implementación del Árbol Binario de Expresión[cite: 6480].
 * Esta es una clase específica, no un TDA genérico.
 */

// Clase interna auxiliar para el nodo
class NodoExpresion {
    String valor; // Operador (+, -, *, /) [cite: 6481] o Número (operando) [cite: 6482]
    NodoExpresion izq;
    NodoExpresion der;

    // Constructor para hojas (números)
    public NodoExpresion(int valorNum) {
        this.valor = String.valueOf(valorNum);
        this.izq = null;
        this.der = null;
    }

    // Constructor para nodos internos (operadores)
    public NodoExpresion(String op, NodoExpresion izq, NodoExpresion der) {
        this.valor = op;
        this.izq = izq;
        this.der = der;
    }

    // Método para saber si es un número (hoja)
    public boolean esHoja() {
        return this.izq == null && this.der == null; 
    }
}

// Clase principal
public class ArbolExpresion {

    private NodoExpresion raiz;

    // Constructor público para Hojas (Números)
    public ArbolExpresion(int valor) {
        this.raiz = new NodoExpresion(valor);
    }

    // Constructor público para Nodos Internos (Operadores)
    public ArbolExpresion(String op, ArbolExpresion izq, ArbolExpresion der) {
        this.raiz = new NodoExpresion(op, izq.raiz, der.raiz);
    }

    /**
     * Evalúa el árbol en forma recursiva [cite: 6483]
     */
    public int evaluar() {
        return evaluarRecursivo(this.raiz);
    }

    private int evaluarRecursivo(NodoExpresion nodo) {

        // Caso Base: Es una hoja (número), devolvemos su valor [cite: 6482]
        if (nodo.esHoja()) {
            return Integer.parseInt(nodo.valor);
        }

        // Caso Recursivo: Es un operador [cite: 6481]
        int valIzq = evaluarRecursivo(nodo.izq);
        int valDer = evaluarRecursivo(nodo.der);

        switch (nodo.valor) {
            case "+":
                return valIzq + valDer;
            case "-":
                return valIzq - valDer;
            case "*":
                return valIzq * valDer;
            case "/":
                if (valDer == 0) {
                    System.out.println("ADVERTENCIA: División por cero detectada.");
                    return 0; 
                }
                return valIzq / valDer;
            default:
                return 0; // Operador no válido
        }
    }

    @Override
    public String toString() {
        return toStringRecursivo(this.raiz);
    }

    /**
     * Método recursivo auxiliar para generar el String de la expresión
     * usando un recorrido In-Order.
     */
    private String toStringRecursivo(NodoExpresion nodo) {
        if (nodo.esHoja()) {
            return nodo.valor;
        }
        String izq = toStringRecursivo(nodo.izq);
        String der = toStringRecursivo(nodo.der);
        return "(" + izq + " " + nodo.valor + " " + der + ")";
    }
}