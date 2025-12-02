package tda;

import priorityQueue.PriorityQueue;

/*
 * Interfaz del TDA Mapa<E> definida en el Parcial 2 [cite: 1917-1934]
 */
public interface MapaTDA<E> {

    // Agrega una ciudad al mapa [cite: 1925]
    void agregarCiudad(E ciudad);

    // Elimina la ciudad y todas sus rutas [cite: 1926]
    void eliminarCiudad(E ciudad);

    // Crea una ruta bidireccional [cite: 1927]
    void conectar(E origen, E destino, int distancia);

    // Genera una Lista de ciudades conectadas [cite: 1928]
    ListaTDA<E> conectadas(E ciudad);

    // Devuelve las ciudades conectadas con menor distancia [cite: 1929]
    ParCiudades<E> masCercanas();

    // Devuelve una cola con prioridad de rutas <= distancia [cite: 1930-1931]
    PriorityQueue<Integer, ParCiudades<E>> conectadasPor(int distancia);

    // Imprime el mapa [cite: 1934]
    void mostrarMapa();
}