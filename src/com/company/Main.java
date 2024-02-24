package com.company;
import java.util.Scanner;

/**
 * Clase Main refactorizada
 * Esta clase devuelve un vector que contiene los numeros primos a partir del 1 (sin incluir) hasta el
 * valor que introzucamos.
 * @author Alexandru_Eduard_Musat
 * @version 1.0
 *
 */
public class Main {
    public static void main(String[] args) {
        Scanner teclado=new Scanner(System.in);
        System.out.println("Introduce el número para la criba de Erastótenes:");
        vectorDePrimos(teclado);
        /**
         * Este es el main.
         * @param teclado recibe un valor por teclado
         * @return vectorDePrimos accede al método vectorDePrimos y le pasa un valor introducido por teclado
         */
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
        /**
         * Este es el metodo que devuelve el vector con los numeros primos.
         * @param dato es un valor entero que corresponde con el final del calculo de numeros primos
         * @param vector es un vector que se crea a partir del método generarPrimos
         */
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
        /**
         * Este es el metodo para crear un vector entre el 1 (incluido) y el valor que le pasemos por teclado.
         * @param dato es el valor introducido por teclado
         * @param vector es el vector que tendra de tamaño el mismo valor que le hayamos pasado por teclado.
         * El for rellena el vector de datos.
         */
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
        /**
         * Metodo que rellena el vector de numeros primos.
         * @param primos es un vector que tendra el tamaño de la cantidad de numeros primos.
         */
    }

    private static int contarPrimos(Result result) {
        int i;
        int cuenta = 0;
        for (i=0; i< result.dim(); i++) {
            if (result.esPrimo()[i])
                cuenta++;
        }
        return cuenta;
        /**
         * Metodo que cuenta la cantidad de números que son primos.
         * @param cuenta cuenta la cantidad de numeros primos en el vector.
         */
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
        /**
         * Método que inicializa el array de primos y elimina los valores 0 y 1.
         */
    }

    private static void eliminarElCeroYElUno(boolean[] esPrimo) {
        esPrimo[0] = esPrimo[1] = false;
        /**
         * Metoddo que define el 0 y el 1 como no primos.
         */
    }

    private static void inicializarArray(int dim, boolean[] esPrimo) {
        int i;
        for (i=0; i< dim; i++)
            esPrimo[i] = true;
        /**
         * Metodo que define como true los valores que sean primos.
         * @return esPrimo devuelve
         *                      <ul>
         *                          <li>true si es primo</li>
         *                          <li>false si no es primo</li>
         *                      </ul>
         */
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
        /**
         * Método que elimina los múltiplos de i.
         */
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
        /**
         * Método que genera los numeros primos y que utiliza los demas métodos para ello.
         */
    }
}