/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practicaia;

/**
 *
 * @author Rodrigo
 */
public class MetodoGaussJordan {

    static void gaussJordan(double[][] matriz, int filas, int columnas) {
        for (int i = 0; i < filas; i++) {
            // Hacer que el elemento diagonal sea 1
            double divisor = matriz[i][i];
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] /= divisor;
            }

            // Hacer ceros en las otras filas
            for (int k = 0; k < filas; k++) {
                if (k != i) {
                    double factor = matriz[k][i];
                    for (int j = 0; j < columnas; j++) {
                        matriz[k][j] -= factor * matriz[i][j];
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        double[][] matriz = {
            {2, 1, -1, 8},
            {-3, -1, 2, -11},
            {-2, 1, 2, -3}
        };

        int filas = matriz.length;
        int columnas = matriz[0].length;

        gaussJordan(matriz, filas, columnas);

        // Imprimir la solución
        for (int i = 0; i < filas; i++) {
            System.out.println("x" + (i + 1) + " = " + matriz[i][columnas - 1]);
        }
    }

}
