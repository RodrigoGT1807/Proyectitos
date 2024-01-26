/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practicaia;
/**
 *
 * @author Rodrigo
 */
import java.util.*;

class Ciudad {
    String nombre;
    Map<Ciudad, Integer> vecinos; // Vecinos y distancias reales (g)
    int h; // Distancia en línea recta a Bucarest

    public Ciudad(String name, int h) {
        this.nombre = name;
        this.h = h;
        this.vecinos = new HashMap<>();
    }

    public void asignarvecino(Ciudad vecino, int distancia) {
        vecinos.put(vecino, distancia);
    }
}

public class BusquedaA {
    public static List<Ciudad> busqueda(Ciudad inicio, Ciudad destino) {
        Map<Ciudad, Integer> valor = new HashMap<>();
        Map<Ciudad, Ciudad> llegaDesde = new HashMap<>();
        PriorityQueue<Ciudad> cola = new PriorityQueue<>(Comparator.comparingInt(ciudad -> valor.get(ciudad) + ciudad.h));

        valor.put(inicio, 0);
        cola.add(inicio);

        while (!cola.isEmpty()) {
            Ciudad actual = cola.poll();
            
            if (actual == destino) {
                return recorrido(llegaDesde, actual);
            }

            for (Map.Entry<Ciudad, Integer> llegada : actual.vecinos.entrySet()) {
                Ciudad vecino = llegada.getKey();
                int funcionA = valor.get(actual) + llegada.getValue();

                if (!valor.containsKey(vecino) || funcionA < valor.get(vecino)) {
                    valor.put(vecino, funcionA);
                    llegaDesde.put(vecino, actual);
                    if (!cola.contains(vecino)) {
                        cola.add(vecino);
                    }
                }
            }
        }

        return null; // No se encontró un camino
    }

    private static List<Ciudad> recorrido(Map<Ciudad, Ciudad> vieneDesde, Ciudad actual) {
        List<Ciudad> path = new ArrayList<>();
        while (actual != null) {
            path.add(actual);
            actual = vieneDesde.get(actual);
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        
        // Creamos las ciudades
        Ciudad arad = new Ciudad("Arad", 366);
        Ciudad lugoj = new Ciudad("Lugoj", 244);
        Ciudad rimnicuVilcea = new Ciudad("Rimnicu Vilcea", 193);
        Ciudad craiova = new Ciudad("Craiova", 160);
        Ciudad mehadia = new Ciudad("Mehadia", 241);
        Ciudad sibiu = new Ciudad("Sibiu", 253);
        Ciudad dobreta = new Ciudad("Dobreta", 242);
        Ciudad oradea = new Ciudad("Oradea", 380);
        Ciudad timisoara = new Ciudad("Timisoara", 329);
        Ciudad fagaras = new Ciudad("Fagaras", 176);
        Ciudad pitesti = new Ciudad("Pitesti", 100);
        Ciudad zerind = new Ciudad("Zerind", 374);
        Ciudad bucarest = new Ciudad("Bucarest", 0);

        // Establecer vecinos y distancias reales (g)
        arad.asignarvecino(lugoj, 244);
        arad.asignarvecino(sibiu, 140);
        arad.asignarvecino(timisoara, 118);
        
        zerind.asignarvecino(arad, 75);
        zerind.asignarvecino(oradea, 71);
        
        timisoara.asignarvecino(arad, 118);
        timisoara.asignarvecino(lugoj, 111);
        
        lugoj.asignarvecino(timisoara, 111);
        lugoj.asignarvecino(mehadia,75);
        
        mehadia.asignarvecino(lugoj, 70);
        mehadia.asignarvecino(dobreta, 75);
        
        dobreta.asignarvecino(mehadia, 75);
        dobreta.asignarvecino(craiova, 120);
        
        craiova.asignarvecino(rimnicuVilcea, 146);
        craiova.asignarvecino(pitesti, 138);
                
        oradea.asignarvecino(sibiu, 151);
        oradea.asignarvecino(zerind, 71);

        sibiu.asignarvecino(oradea, 151);
        sibiu.asignarvecino(fagaras, 99);
        sibiu.asignarvecino(rimnicuVilcea, 80);
        sibiu.asignarvecino(arad, 140);

        rimnicuVilcea.asignarvecino(sibiu, 80);
        rimnicuVilcea.asignarvecino(craiova, 146);
        rimnicuVilcea.asignarvecino(pitesti, 97);

        pitesti.asignarvecino(bucarest, 101);
        pitesti.asignarvecino(craiova, 138);
        pitesti.asignarvecino(rimnicuVilcea, 97);

        fagaras.asignarvecino(sibiu, 99);
        fagaras.asignarvecino(bucarest, 211);

        // Realizar la búsqueda A*
        List<Ciudad> ruta = busqueda(arad, bucarest);

        // Imprimir el camino
        if (ruta != null) {
            for (Ciudad ciudad : ruta) {
                System.out.println(ciudad.nombre);
            }
        } else {
            System.out.println("No se encontró un camino");
        }
    }
}