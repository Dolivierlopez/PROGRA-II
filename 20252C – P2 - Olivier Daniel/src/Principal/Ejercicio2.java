package Principal;

import java.util.Comparator;
import java.util.Scanner; 

// Importamos TDAs del CÓDIGO DE AYUDA
import AVL.AVL;
import AVL.AVLTDA;
import AVL.DefaultComparator;
import priorityQueue.Entrada;
import priorityQueue.Entry;

// Importamos el TDA que creamos
import tda.ArbolExpresion;

/**
 * Clase de testeo para el Ejercicio 2 
 */
public class Ejercicio2 {

    // Instancia estática de Scanner
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("--- INICIO EJERCICIO 2: ARBOL DE EXPRESION ---");

        // b) Generar un AVL con Entradas <Resultado, Expresion> [cite: 6491]
        Comparator<Entry<Integer, String>> comp = new DefaultComparator<>();
        AVLTDA<Entry<Integer, String>> avlResultados = new AVL<>(comp);

        boolean continuar = true;

        // b) Permitir al usuario ingresar varias expresiones [cite: 6491]
        while (continuar) {
            System.out.println("\n--- Ingresar Nueva Expresión ---");

            // a) Construir árbol interactivamente
            System.out.println("A continuación, construya el árbol:");
            ArbolExpresion arbol = crearArbolInteractivo();

            // a) Evaluarlo
            int resultado = arbol.evaluar(); // [cite: 6483]

            // Generar String desde el árbol
            String expTexto = arbol.toString(); 

            System.out.println("\nExpresión construida: " + expTexto);
            System.out.println("Resultado de la expresión: " + resultado);

            // b) Guardar en el AVL
            avlResultados.insert(new Entrada<>(resultado, expTexto)); // [cite: 6491]

            // Preguntar si desea ingresar otra
            System.out.println("\n¿Desea ingresar otra expresion? (s/n)");
            String respuesta = leerString();

            if (respuesta.equalsIgnoreCase("n")) {
                continuar = false;
            }
        }

        // b) Mostrar resultados ordenados de menor a mayor [cite: 6491]
        System.out.println("\n--- Resultados Finales Ordenados por Resultado ---");

        // Usamos el método 'inOrderTraversal()' del AVL
        //
        // que imprime en consola de forma ordenada.
        avlResultados.inOrderTraversal();
        System.out.println(); // Salto de línea

        scanner.close();
        System.out.println("\n--- FIN EJERCICIO 2 ---");
    }

    /**
     * (PARTE A) Método auxiliar recursivo para construir un ArbolExpresion
     */
    private static ArbolExpresion crearArbolInteractivo() {
        int tipo = 0;
        // Validar entrada (1 o 2)
        while(tipo != 1 && tipo != 2) {
            System.out.println("¿Qué desea ingresar? (1: Numero, 2: Operador)");
            tipo = leerInt();
        }

        if (tipo == 1) {
            // Caso Base: Hoja (Operando) [cite: 6482]
            System.out.println("Ingrese el número:");
            int valor = leerInt();
            return new ArbolExpresion(valor);
        } else {
            // Caso Recursivo: Nodo Interno (Operador) [cite: 6481]
            String op = "";
            boolean opValido = false;

            // Validar operador
            while (!opValido) {
                System.out.println("Ingrese el operador (+, -, *, /):");
                op = leerString();

                if (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/")) {
                    opValido = true;
                } else {
                    System.out.println("Error: Operador '" + op + "' no válido.");
                }
            }

            System.out.println("...Construyendo hijo IZQUIERDO para '" + op + "'...");
            ArbolExpresion izq = crearArbolInteractivo();

            System.out.println("...Construyendo hijo DERECHO para '" + op + "'...");
            ArbolExpresion der = crearArbolInteractivo();

            return new ArbolExpresion(op, izq, der);
        }
    }

    private static String leerString() {
        return scanner.nextLine();
    }

    private static int leerInt() {
        while (true) {
            try {
                String num = scanner.nextLine();
                return Integer.parseInt(num); 
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese solo un número. Intente de nuevo.");
            }
        }
    }
}