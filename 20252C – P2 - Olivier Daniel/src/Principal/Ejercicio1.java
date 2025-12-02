package Principal;

import tda.ListaTDA;
import tda.Mapa;
import tda.MapaTDA;
import tda.ParCiudades;
import priorityQueue.Entry;
import priorityQueue.PriorityQueue;

/**
 * Clase de testeo para el Ejercicio 1
 */
public class Ejercicio1 {

    public static void main(String[] args) {
        System.out.println("--- INICIO EJERCICIO 1: TDA MAPA ---");

        // Usamos String para las ciudades (genérico E)
        MapaTDA<String> mapa = new Mapa<String>();

        // 1. agregarCiudad [cite: 1925]
        System.out.println("Agregando ciudades...");
        mapa.agregarCiudad("Buenos Aires");
        mapa.agregarCiudad("Rosario");
        mapa.agregarCiudad("Cordoba");
        mapa.agregarCiudad("Mendoza");
        mapa.agregarCiudad("Bahia Blanca");

        // 2. conectar (Bidireccional) [cite: 1927]
        System.out.println("Creando rutas...");
        mapa.conectar("Buenos Aires", "Rosario", 300);
        mapa.conectar("Buenos Aires", "Bahia Blanca", 650);
        mapa.conectar("Buenos Aires", "Cordoba", 700);
        mapa.conectar("Rosario", "Cordoba", 400);
        mapa.conectar("Cordoba", "Mendoza", 350);
        mapa.conectar("Bahia Blanca", "Mendoza", 800);

        // 3. mostrarMapa [cite: 1934]
        System.out.println("\n--- Prueba mostrarMapa() ---");
        mapa.mostrarMapa();

        // 4. conectadas [cite: 1928]
        System.out.println("\n--- Prueba conectadas(\"Cordoba\") ---");
        ListaTDA<String> conectadasCordoba = mapa.conectadas("Cordoba");
        for (int i = 0; i < conectadasCordoba.size(); i++) {
            System.out.println("- " + conectadasCordoba.get(i));
        }

        // 5. masCercanas [cite: 1929]
        System.out.println("\n--- Prueba masCercanas() ---");
        ParCiudades<String> cercanas = mapa.masCercanas();
        System.out.println("La conexión más corta es: " + cercanas.toString());

        // 6. conectadasPor (Bonus AVL) [cite: 1930-1933]
        System.out.println("\n--- Prueba conectadasPor(400) (Bonus AVL PQ) ---");
        PriorityQueue<Integer, ParCiudades<String>> pq = mapa.conectadasPor(400);
        while (!pq.isEmpty()) {
            // removeMin() nos dará las rutas ordenadas por distancia
            Entry<Integer, ParCiudades<String>> e = pq.removeMin();
            System.out.println("Dist: " + e.getKey() + "km, Ruta: " + e.getValue().toString());
        }

        // 7. eliminarCiudad [cite: 1926]
        System.out.println("\n--- Prueba eliminarCiudad(\"Rosario\") ---");
        mapa.eliminarCiudad("Rosario");
        mapa.mostrarMapa();

        System.out.println("\n--- FIN EJERCICIO 1 ---");
    }
}