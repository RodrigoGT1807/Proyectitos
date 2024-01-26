package com.mycompany.practicaia;
/**
 *
 * @author Rodrigo
 */
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BusquedaRapida {

    private final int x;

    public BusquedaRapida() {
        this.x = generarAleatorio();
    }

    public int generarAleatorio() {
        Random random = new Random();
        return random.nextInt(300000000) + 1;
    }

    public int funcionPrincipal() {
        return x ^ 2 + 2 * x + 5;
    }

    // 1.1.
    public static int maximo(int[] array) {
        return Arrays.stream(array).max().orElseThrow();
    }

    // 1.3
    public static int busquedaRapida(int[] array, int numero) {
        Arrays.sort(array);
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("---RESULTADOS DE LA EJECUCION ORDENADA----");

        
        int izq = 0;
        int der = array.length - 1;
        while (izq <= der) {
            int mid = izq + (der - izq) / 2;
            if (array[mid] == numero) {
                return mid;
            } else if (array[mid] < numero) {
                izq = mid + 1;
            } else {
                der = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cantidad = 300000;
        int[] resultados = new int[cantidad];
        long inicio = System.currentTimeMillis();
        for (int i = 0; i < cantidad; i++) {
            BusquedaRapida n = new BusquedaRapida();
            resultados[i] = n.funcionPrincipal();
        }


        System.out.println("---RESULTADOS DE LA EJECUCION SIN ORDENAR----");
        for (int resultado : resultados) {
            System.out.println(resultado);
        }

        long fin = System.currentTimeMillis();
        int max = maximo(resultados);

        System.out.println("---EL MAXIMO VALOR ES-----");
        System.out.println(max);
        System.out.println("------BUSQUEDA RAPIDA-----------");
        System.out.println("----INGRESE EL NUMERO QUE DESEE BUSCAR-----");
        int busqueda = sc.nextInt();
        long inicioBusqueda = System.currentTimeMillis();
        int indice = busquedaRapida(resultados, busqueda);
        long finBusqueda = System.currentTimeMillis();

        if (indice != -1) {
            System.out.println("El resultado se encuentra en la posicion: " + (indice + 1));
        } else {
            System.out.println("El numero no se encuentra en dicha posicion");
        }

        System.out.println(" ");
        System.out.println(" ");

        System.out.println("---DATOS DE TIEMPO-----");

        long tiempoTotal = fin - inicio;
        System.out.println("Tiempo total de ejecución: " + tiempoTotal + " ms");

        long tiempoBusqueda = finBusqueda - inicioBusqueda;

        System.out.println("Tiempo de ejecución de la búsqueda secuencial: " + tiempoBusqueda + " ms");

    }
}
