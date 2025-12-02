package main;

import tda.implementaciones.GrafoEstatico;
import tda.implementaciones.ListaDinamica;
import tda.interfaces.GrafoTDA;
import tda.interfaces.ListaTDA;

public class main {

    public static void main(String[] args) {

        GrafoTDA<Integer> miGrafo = new GrafoEstatico<>();

        System.out.println("--- Agregando Vertices ---");
        miGrafo.agregarVertice(10);
        miGrafo.agregarVertice(20);
        miGrafo.agregarVertice(30);
        miGrafo.agregarVertice(40);

        System.out.println("--- Agregando Aristas ---");
        miGrafo.agregarArista(10, 20, 5);
        miGrafo.agregarArista(10, 30, 15);
        miGrafo.agregarArista(20, 30, 8);
        miGrafo.agregarArista(40, 30, 3);
        miGrafo.agregarArista(30, 10, 2);

        System.out.println("\n--- Vertices del Grafo ---");

        // --- ERROR 4 CORREGIDO (Error de ejecucion ClassCastException) ---
        // La linea original era: Integer[] vertices = (Integer[]) miGrafo.vertices();
        Object[] vertices = miGrafo.vertices(); // Se recibe como Object[]

        System.out.print("[ ");
        for (Object vObj : vertices) {
            // Se castea cada elemento individualmente
            System.out.print( (Integer)vObj + " ");
        }
        System.out.println("]");
        // --- Fin Correccion 4 ---

        System.out.println("\n--- Prueba Mayor Costo Saliente ---");
        System.out.println("Mayor costo saliente de 10: " + miGrafo.mayorCostoSalientes(10));
        System.out.println("Mayor costo saliente de 20: " + miGrafo.mayorCostoSalientes(20));
        System.out.println("Mayor costo saliente de 40: " + miGrafo.mayorCostoSalientes(40));

        System.out.println("\n--- Prueba Predecesores ---");
        ListaTDA<Integer> predecesores30 = miGrafo.predecesores(30);
        System.out.print("Predecesores de 30: ");
        imprimirLista(predecesores30);

        ListaTDA<Integer> predecesores10 = miGrafo.predecesores(10);
        System.out.print("Predecesores de 10: ");
        imprimirLista(predecesores10);

        ListaTDA<Integer> predecesores40 = miGrafo.predecesores(40);
        System.out.print("Predecesores de 40: ");
        imprimirLista(predecesores40);

        System.out.println("\n--- Prueba Eliminacion ---");
        System.out.println("Eliminando vertice 20...");
        miGrafo.eliminarVertice(20);

        System.out.println("Vertices restantes:");

        // --- ERROR 5 CORREGIDO (Mismo ClassCastException de antes) ---
        vertices = miGrafo.vertices(); // Se recibe como Object[]

        System.out.print("[ ");
        for (Object vObj : vertices) {
            // (El orden puede cambiar, 20 se pisa con el ultimo: 40)
            System.out.print( (Integer)vObj + " ");
        }
        System.out.println("]");
        // --- Fin Correccion 5 ---

        System.out.println("¿Existe arista (10, 20)?: " + miGrafo.existeArista(10, 20));
        System.out.println("¿Existe arista (10, 40)?: " + miGrafo.existeArista(10, 40));

        predecesores30 = miGrafo.predecesores(30);
        System.out.print("Nuevos predecesores de 30: ");
        imprimirLista(predecesores30);
    }

    public static <E> void imprimirLista(ListaTDA<E> lista) {
        if (lista == null) {
            System.out.println("(null)");
            return;
        }
        if (lista.estaVacia()) {
            System.out.println("[]");
            return;
        }

        System.out.print("[ ");
        for (int i = 0; i < lista.tamanio(); i++) {
            System.out.print(lista.obtener(i));
            if (i < lista.tamanio() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" ]");
    }
}