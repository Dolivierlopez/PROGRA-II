package tda;

// Importamos las clases base del código de ayuda
import grafo.GrafoDin;
import grafo.GrafoTDA;
import priorityQueue.PriorityQueue;

/**
 * Implementación del TDA Mapa<E>.
 * Se utiliza un Grafo Dinámico (GrafoDin) del código de ayuda como base.
 */
public class Mapa<E> implements MapaTDA<E> {

    private GrafoTDA<E> grafo; // El Mapa "usa" un Grafo

    public Mapa() {
        // Usamos la implementación dinámica del código de ayuda
        this.grafo = new GrafoDin<E>(); 
    }

    public void agregarCiudad(E ciudad) {
        grafo.agregarVertice(ciudad); //
    }

    public void eliminarCiudad(E ciudad) {
        grafo.eliminarVertice(ciudad); //
    }

    // Crea una ruta bidireccional [cite: 1927]
    public void conectar(E origen, E destino, int distancia) {
        grafo.agregarArista(origen, destino, distancia);
        grafo.agregarArista(destino, origen, distancia); // Bidireccional
    }

    // Genera una Lista de ciudades conectadas [cite: 1928]
    public ListaTDA<E> conectadas(E ciudad) {
        ListaTDA<E> lista = new ListaEnlazada<E>();
        lista.InicializarLista();

        E[] todas = grafo.vertices(); //

        // Recorremos el array de vértices devuelto por el grafo
        // El array de GrafoDin tiene el tamaño exacto
        for (int i = 0; i < todas.length; i++) {
            E v = todas[i];
            if (v == null) continue; 

            // Si existe arista desde 'ciudad' a 'v'
            if (grafo.existeArista(ciudad, v)) {
                lista.add(v);
            }
        }
        return lista;
    }

    // Devuelve las ciudades conectadas con menor distancia [cite: 1929]
    public ParCiudades<E> masCercanas() {
        int distMin = Integer.MAX_VALUE;
        ParCiudades<E> parMin = null;
        E[] vertices = grafo.vertices(); //

        for (int i = 0; i < vertices.length; i++) {
            E v1 = vertices[i];
            if (v1 == null) continue;

            // Empezamos j=i+1 para evitar (A,A) y (B,A) si ya vimos (A,B)
            for (int j = i + 1; j < vertices.length; j++) { 
                E v2 = vertices[j];
                if (v2 == null) continue;

                if (grafo.existeArista(v1, v2)) {
                    int peso = grafo.pesoArista(v1, v2);
                    if (peso < distMin) {
                        distMin = peso;
                        parMin = new ParCiudades<E>(v1, v2);
                    }
                }
            }
        }
        return parMin;
    }

    // Genera una cola con prioridad [cite: 1930-1933]
    public PriorityQueue<Integer, ParCiudades<E>> conectadasPor(int distancia) {
        // Usamos la implementación BONUS
        PriorityQueue<Integer, ParCiudades<E>> pq = new AVLPriorityQueue<>();

        E[] vertices = grafo.vertices(); //

        for (int i = 0; i < vertices.length; i++) {
            E v1 = vertices[i];
            if (v1 == null) continue;

            // Empezamos j=i+1 para evitar duplicados (B,A)
            for (int j = i + 1; j < vertices.length; j++) {
                E v2 = vertices[j];
                if (v2 == null) continue; 

                if (grafo.existeArista(v1, v2)) {
                    int peso = grafo.pesoArista(v1, v2);
                    if (peso <= distancia) {
                        // La clave es la distancia, el valor es el Par
                        pq.insert(peso, new ParCiudades<E>(v1, v2));
                    }
                }
            }
        }
        return pq;
    }

    // Imprime el mapa [cite: 1934]
    public void mostrarMapa() {
        E[] vertices = grafo.vertices(); //

        for (int i=0; i < vertices.length; i++) {
            E v1 = vertices[i];
            if (v1 == null) continue;

            System.out.print("Ciudad: " + v1 + " -> ");

            ListaTDA<E> conexiones = this.conectadas(v1);
            if (conexiones.isEmpty()) {
                System.out.println("[Sin rutas]");
                continue;
            }

            for (int j = 0; j < conexiones.size(); j++) {
                E v2 = conexiones.get(j);
                int peso = grafo.pesoArista(v1, v2);
                System.out.print(v2 + " (" + peso + "km) | ");
            }
            System.out.println();
        }
    }
}