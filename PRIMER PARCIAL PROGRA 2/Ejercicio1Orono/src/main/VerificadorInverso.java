package main;

import stack.LinkedStack;
import stack.Stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class VerificadorInverso {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Introduce la primera cadena:");
        String cadena1 = reader.readLine();

        System.out.println("Introduce la segunda cadena:");
        String cadena2 = reader.readLine();

        boolean resultado = sonInversasEspeciales(cadena1, cadena2);

        System.out.println("---");
        System.out.println("Resultado: " + resultado);
    }

    public static boolean sonInversasEspeciales(String c1, String c2) {
        Stack<Character> pilaDeLetras = new LinkedStack<>();
        int sumaDigitos1 = 0;
        int sumaDigitos2 = 0;

        for (int i = 0; i < c1.length(); i++) {
            char caracter = c1.charAt(i);
            if (Character.isLetter(caracter)) {
                pilaDeLetras.push(caracter);
            } else if (Character.isDigit(caracter)) {
                sumaDigitos1 += Character.getNumericValue(caracter);
            }
        }

        for (int i = 0; i < c2.length(); i++) {
            char caracter = c2.charAt(i);
            if (Character.isLetter(caracter)) {
                if (pilaDeLetras.isEmpty()) {
                    return false;
                }
                if (pilaDeLetras.pop() != caracter) {
                    return false;
                }
            } else if (Character.isDigit(caracter)) {
                sumaDigitos2 += Character.getNumericValue(caracter);
            }
        }
        return pilaDeLetras.isEmpty() && (sumaDigitos1 == sumaDigitos2);
    }
}