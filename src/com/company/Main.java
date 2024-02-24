package com.company;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner teclado=new Scanner(System.in);
        System.out.println("Introduce el número para la criba de Erastótenes:");
        vectorDePrimos(teclado);
    }

    private static void vectorDePrimos(Scanner teclado) {
        int dato = vectorInicial(teclado);
        int[] vector;
        vector=generarPrimos(dato);
        System.out.println("\nVector de primos hasta:"+dato);
        for (int i = 0; i < vector.length; i++) {
            if (i%10==0) System.out.println();
            System.out.print(vector[i]+"\t");
        }
    }

    private static int vectorInicial(Scanner teclado) {
        int dato= teclado.nextInt();
        int vector[]=new int[dato];
        System.out.println("\nVector inicial hasta :"+dato);
        for (int i = 0; i < vector.length; i++) {
            if (i%10==0) System.out.println();
            System.out.print(i+1+"\t");
        }
        return dato;
    }
    private static int[] rellenarVectorConNumerosPrimos(int cuenta, Result result) {
        int i;
        int j;
        int[] primos = new int[cuenta];
        for (i=0, j=0; i< result.dim(); i++) {
            if (result.esPrimo()[i])
                primos[j++] = i;
        }
        return primos;
    }

    private static int contarPrimos(Result result) {
        int i;
        int cuenta = 0;
        for (i=0; i< result.dim(); i++) {
            if (result.esPrimo()[i])
                cuenta++;
        }
        return cuenta;
    }

    private static Result getResult(int max) {
        int dim = max + 1; // Tamaño del array
        boolean[] esPrimo = new boolean[dim];
        // Inicializar el array
        inicializarArray(dim, esPrimo);
        // Eliminar el 0 y el 1, que no son primos
        eliminarElCeroYElUno(esPrimo);
        Result result = new Result(dim, esPrimo);
        return result;
    }

    private static void eliminarElCeroYElUno(boolean[] esPrimo) {
        esPrimo[0] = esPrimo[1] = false;
    }

    private static void inicializarArray(int dim, boolean[] esPrimo) {
        int i;
        for (i=0; i< dim; i++)
            esPrimo[i] = true;
    }

    private record Result(int dim, boolean[] esPrimo) {
    }

    private static void criba(int dim, boolean[] esPrimo) {
        int j;
        int i;
        for (i=2; i<Math.sqrt(dim)+1; i++) {
            if (esPrimo[i]) {
                // Eliminar los múltiplos de i
                for (j=2*i; j< dim; j+=i)
                    esPrimo[j] = false;
            }
        }
    }
    // Generar números primos de 1 a max
    public static int[] generarPrimos (int max)
    {
        int i,j;
        if (max >= 2) {
            // Declaraciones
            Result result = getResult(max);
            // Criba
            criba(result.dim(), result.esPrimo());
            // ¿Cuántos primos hay?
            int cuenta = contarPrimos(result);
            // Rellenar el vector de números primos
            return rellenarVectorConNumerosPrimos(cuenta, result);
        } else { // max < 2
            return new int[0];
            // Vector vacío
        }
    }
}