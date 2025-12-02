package main;

import matrix.ListSparseMatrix;
import matrix.MatrixEntry;
import matrix.SparseMatrix;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class PruebaMatrizDispersa {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("--- Creación de Matrices Dispersas ---");
        System.out.print("Ingrese las dimensiones para la Matriz 1 (ej: 100 100): ");
        reader.readLine();
        System.out.print("Ingrese las dimensiones para la Matriz 2 (ej: 100 100): ");
        reader.readLine();

        SparseMatrix<String> m1 = new ListSparseMatrix<>();
        SparseMatrix<String> m2 = new ListSparseMatrix<>();

        System.out.println("\n--- Carga de Valores ---");
        System.out.println("Ingrese 2 valores para la Matriz 1 (fila columna valor):");
        cargarValor(reader, m1);
        cargarValor(reader, m1);

        System.out.println("\nIngrese 2 valores para la Matriz 2 (fila columna valor):");
        cargarValor(reader, m2);
        cargarValor(reader, m2);

        System.out.println("\n--- Matriz 1 ---");
        imprimirMatriz(m1);

        System.out.println("\n--- Matriz 2 ---");
        imprimirMatriz(m2);

        System.out.println("\n--- Suma de Matriz 1 + Matriz 2 ---");
        SparseMatrix<String> suma = m1.adds(m2);
        imprimirMatriz(suma);

        System.out.println("\n--- Traspuesta de la Suma ---");
        SparseMatrix<String> traspuestaSuma = suma.transpose();
        imprimirMatriz(traspuestaSuma);

        System.out.println("\n--- Suma convertida a Diccionario por Filas (consigna d) ---");
        System.out.println(suma.aDiccionario());
    }

    private static void cargarValor(BufferedReader reader, SparseMatrix<String> matrix) throws IOException {
        System.out.print("-> ");
        String[] input = reader.readLine().split(" ");
        if (input.length == 3) {
            int fila = Integer.parseInt(input[0]);
            int col = Integer.parseInt(input[1]);
            String valor = input[2];
            matrix.put(fila, col, valor);
        } else {
            System.out.println("Entrada inválida.");
        }
    }

    private static <E> void imprimirMatriz(SparseMatrix<E> matrix) {
        if (matrix.size() == 0) {
            System.out.println("(Matriz vacía)");
            return;
        }
        MatrixEntry<E>[] values = matrix.values();
        // BUCLE FOR CON INDICE
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
        }
    }
}