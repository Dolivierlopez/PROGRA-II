package eje3;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        GrafoDin<String> grafo = new GrafoDin<>();

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");
        grafo.agregarVertice("D");
        grafo.agregarVertice("E");

        grafo.agregarArista("A", "B", 1);
        grafo.agregarArista("B", "C", 1);
        grafo.agregarArista("A", "D", 1);
        grafo.agregarArista("D", "C", 1);

        LinkedList<String> aislados = grafo.verticesAislados();
        System.out.println("🔹 Vértices aislados: " + aislados);

        LinkedList<String> puentes = grafo.verticesPuente("A", "C");
        System.out.println("🔹 Vértices puente entre A y C: " + puentes);
    }
}
