package tda;

import java.util.Comparator;

// Importamos las clases del código de ayuda de los paquetes
import AVL.AVL;
import AVL.DefaultComparator;
import priorityQueue.Entrada;
import priorityQueue.Entry;
import priorityQueue.MyException;
import priorityQueue.PriorityQueue;

/**
 * Implementación del TDA PriorityQueue usando un AVL como estructura base.
 * Esto cumple con la calificación extra (Bonus) del Ejercicio 1.
 */
public class AVLPriorityQueue<K extends Comparable<K>, V> implements PriorityQueue<K, V> {

    // 1. Usamos el AVL del código de ayuda
    private AVL<Entry<K, V>> arbol;
    private int contador;

    // 2. Usamos el DefaultComparator del código de ayuda
    public AVLPriorityQueue() {
        // El AVL necesita un comparador.
        // Usamos DefaultComparator para comparar Entradas (basado en la clave K).
        Comparator<Entry<K, V>> comp = new DefaultComparator<>();
        this.arbol = new AVL<>(comp);
        this.contador = 0;
    }

    public int size() {
        return this.contador;
    }

    public boolean isEmpty() {
        return (this.contador == 0);
    }

    // insert() de PQ usa insert() de AVL 
    public void insert(K key, V value) {
        // Creamos una Entrada (del paquete priorityQueue)
        Entry<K, V> nuevaEntrada = new Entrada<>(key, value);
        this.arbol.insert(nuevaEntrada); // Usa el insert() del AVL
        this.contador++;
    }

    // min() de PQ usa el getMinElement() que agregamos al AVL 
    public Entry<K, V> min() {
        if (isEmpty()) {
            throw new MyException("Cola con prioridad vacia");
        }
        return this.arbol.getMinElement();
    }

    // removeMin() de PQ usa min() y delete() de AVL 
    public Entry<K, V> removeMin() {
        if (isEmpty()) {
            throw new MyException("Cola con prioridad vacia");
        }
        Entry<K, V> entradaMinima = this.min();
        this.arbol.delete(entradaMinima); // Usa el delete() del AVL
        this.contador--;
        return entradaMinima;
    }
}