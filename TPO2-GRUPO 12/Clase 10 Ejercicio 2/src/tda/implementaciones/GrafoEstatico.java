package tda.implementaciones;

import tda.interfaces.GrafoTDA;
import tda.interfaces.ListaTDA;
import tda.implementaciones.ListaDinamica;

public class GrafoEstatico<E> implements GrafoTDA<E> {

    private static final int MAX_VERTICES = 100;
    private int[][] MAdy;
    private E[] etiquetas;
    private int cantVertices;

    @SuppressWarnings("unchecked")
    public GrafoEstatico() {
        this.MAdy = new int[MAX_VERTICES][MAX_VERTICES];
        this.etiquetas = (E[]) new Object[MAX_VERTICES];
        this.cantVertices = 0;
    }

    private int verticeAIndice(E v) {
        for (int i = 0; i < this.cantVertices; i++) {
            if (this.etiquetas[i].equals(v)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void agregarVertice(E v) {
        if (this.cantVertices >= MAX_VERTICES) {
            System.err.println("Error: No se pueden agregar mas vertices.");
            return;
        }
        if (verticeAIndice(v) != -1) {
            System.err.println("Error: El vertice ya existe.");
            return;
        }

        this.etiquetas[this.cantVertices] = v;
        this.cantVertices++;
    }

    @Override
    public void eliminarVertice(E v) {
        int indiceEliminar = verticeAIndice(v);
        if (indiceEliminar == -1) {
            System.err.println("Error: El vertice no existe.");
            return;
        }

        int ultimoIndice = this.cantVertices - 1;

        // Pisamos la etiqueta del vertice a eliminar
        this.etiquetas[indiceEliminar] = this.etiquetas[ultimoIndice];

        // Copiamos la FILA del ultimo vertice sobre la fila a eliminar
        for (int i = 0; i < MAX_VERTICES; i++) {
            this.MAdy[indiceEliminar][i] = this.MAdy[ultimoIndice][i];
        }

        // Copiamos la COLUMNA del ultimo vertice sobre la columna a eliminar
        for (int i = 0; i < MAX_VERTICES; i++) {
            this.MAdy[i][indiceEliminar] = this.MAdy[i][ultimoIndice];
        }

        // Limpiamos la ultima fila y columna (ahora duplicadas)
        for (int i = 0; i < MAX_VERTICES; i++) {
            this.MAdy[ultimoIndice][i] = 0;
            this.MAdy[i][ultimoIndice] = 0;
        }

        this.etiquetas[ultimoIndice] = null;
        this.cantVertices--;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E[] vertices() {
        E[] resultado = (E[]) new Object[this.cantVertices];

        for(int i = 0; i < this.cantVertices; i++) {
            resultado[i] = this.etiquetas[i];
        }
        return resultado;
    }

    @Override
    public void agregarArista(E v1, E v2, int peso) {
        int i = verticeAIndice(v1);
        int j = verticeAIndice(v2);

        if (i == -1 || j == -1) {
            System.err.println("Error: Uno o ambos vertices no existen.");
            return;
        }
        if (peso <= 0) {
            System.err.println("Error: El peso debe ser positivo.");
            return;
        }

        this.MAdy[i][j] = peso;
    }

    @Override
    public void eliminarArista(E v1, E v2) {
        int i = verticeAIndice(v1);
        int j = verticeAIndice(v2);

        if (i != -1 && j != -1) {
            this.MAdy[i][j] = 0;
        }
    }

    @Override
    public boolean existeArista(E v1, E v2) {
        int i = verticeAIndice(v1);
        int j = verticeAIndice(v2);

        if (i == -1 || j == -1) {
            return false;
        }

        return this.MAdy[i][j] != 0;
    }

    @Override
    public int pesoArista(E v1, E v2) {
        int i = verticeAIndice(v1);
        int j = verticeAIndice(v2);

        if (i == -1 || j == -1) {
            return 0;
        }

        return this.MAdy[i][j];
    }

    @Override
    public int mayorCostoSalientes(E v) {
        int i = verticeAIndice(v);
        if (i == -1) {
            System.err.println("Error: El vertice no existe.");
            return -1;
        }

        int maxCosto = 0;
        // Recorremos la FILA 'i'
        for (int j = 0; j < this.cantVertices; j++) {
            int pesoActual = this.MAdy[i][j];
            if (pesoActual > maxCosto) {
                maxCosto = pesoActual;
            }
        }
        return maxCosto;
    }

    @Override
    public ListaTDA<E> predecesores(E v) {
        int j = verticeAIndice(v);
        if (j == -1) {
            System.err.println("Error: El vertice no existe.");
            return null;
        }

        ListaTDA<E> listaPredecesores = new ListaDinamica<>();

        // Recorremos la COLUMNA 'j'
        for (int i = 0; i < this.cantVertices; i++) {
            if (this.MAdy[i][j] != 0) {
                listaPredecesores.agregar(this.etiquetas[i]);
            }
        }
        return listaPredecesores;
    }
}