package com.mycompany.practicaia;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class busqsecuencial {

    private final int x;

    public busqsecuencial() {
        this.x = generaraleatorio();
    }

    public int generaraleatorio() {
        Random random = new Random();
        return random.nextInt(300000000) + 1;
    }

    public int funcionprincipal() {
        return x ^ 2 + 2 * x + 5;
    }
    //1.1.

    public static int maximo(int[] array) {
        return Arrays.stream(array).max().orElseThrow();
    }

    //1.2
    public static int busqsecuencial(int[] array, int numero) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == numero) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int cantidad = 3000000;
        int[] resultados = new int[cantidad];
        long inicio = System.currentTimeMillis();

        for (int i = 0; i < cantidad; i++) {
            busqsecuencial n = new busqsecuencial();
            resultados[i] = n.funcionprincipal();
        }

        System.out.println("---RESULTADOS DE LA EJECUCION----");

        for (int resultado : resultados) {
            System.out.println(resultado);
        }
        long fin = System.currentTimeMillis();

        int max = maximo(resultados);

        System.out.println("---EL MAXIMO VALOR ES-----");
        System.out.println(max);
        System.out.println("------BUSQUEDA SECUENCIAL-----------");
        System.out.println("----INGRESE EL NUMERO QUE DESEE BUSCAR-----");
        int busqueda = sc.nextInt();
        long inicioBusqueda = System.currentTimeMillis();
        int indice = busqsecuencial(resultados, busqueda);
        long finBusqueda = System.currentTimeMillis();

        if (indice != -1) {
            System.out.println("El resultado se encuentra en la posicion:" + (indice + 1));

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
