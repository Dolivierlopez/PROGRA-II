package main;

import tda.implementaciones.AVL;
import tda.interfaces.AVLTDA;
import tda.interfaces.ListaTDA;

// Importamos las unicas clases de Java permitidas (para IO)
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        // 1. Inicializar el Arbol AVL
        AVLTDA<Integer> arbol = new AVL<>();

        // 2. Preparar la entrada de datos (sin Scanner)
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("--- Carga de Arbol AVL ---");

        while (true) {
            System.out.print("Ingrese un numero (o '.' para detener): ");
            String linea = reader.readLine();

            if (linea.equals(".")) {
                break; // Detener con el símbolo "."
            }

            try {
                // Convertimos el input a numero
                int numero = Integer.parseInt(linea);

                // Insertamos en el AVL
                arbol.insertar(numero);
                System.out.println("   -> Insertado: " + numero);

            } catch (NumberFormatException e) {
                System.out.println("   -> Error: Ingrese solo numeros o '.' para detener.");
            }
        }

        if (arbol.estaVacio()) {
            System.out.println("\nEl arbol esta vacio. No hay nada que mostrar.");
            return;
        }

        // 3. Mostrar el árbol generado por niveles (Salida 1)
        System.out.println("\n--- Recorrido por Niveles ---");
        ListaTDA<Integer> listaPorNiveles = arbol.recorridoPorNiveles();

        for (int i = 0; i < listaPorNiveles.tamanio(); i++) {
            System.out.print(listaPorNiveles.obtener(i) + " ");
        }
        System.out.println();


        // 4. Generar y mostrar el arreglo ordenado (Salida 2 y 3)
        System.out.println("\n--- Arreglo Ordenado (In-Order) ---");

        // Obtenemos la lista ordenada (In-Order)
        ListaTDA<Integer> listaOrdenada = arbol.obtenerElementosOrdenados();

        // Creamos un arreglo nativo desde cero
        Integer[] arregloOrdenado = new Integer[listaOrdenada.tamanio()];

        // Copiamos de nuestra ListaTDA al arreglo nativo
        for (int i = 0; i < listaOrdenada.tamanio(); i++) {
            arregloOrdenado[i] = listaOrdenada.obtener(i);
        }

        // Mostramos el arreglo
        System.out.print("[ ");
        for (int i = 0; i < arregloOrdenado.length; i++) {
            System.out.print(arregloOrdenado[i]);
            if (i < arregloOrdenado.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" ]");
    }
}