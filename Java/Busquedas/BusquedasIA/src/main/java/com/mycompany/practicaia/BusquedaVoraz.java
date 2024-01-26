
package com.mycompany.practicaia;
import java.util.*;

class Ciudad {
    private final String nombre;
    private final Map<Ciudad, Integer> vecinos = new HashMap<>();

    public Ciudad(String nombre) {
        this.nombre = nombre;
    }   

    public void agregarVecino(Ciudad ciudad, int distancia) {
        vecinos.put(ciudad, distancia);
        ciudad.vecinos.put(this, distancia); 
    }

    public String getNombre() {
        return nombre;
    }

    public Map<Ciudad, Integer> getVecinos() {
        return vecinos;
    }
}

public class BusquedaVoraz {

    public static void main(String[] args) {
        Ciudad arad = new Ciudad("Arad");
        Ciudad sibiu = new Ciudad("Sibiu");
        Ciudad fagaras = new Ciudad("Fagaras");
        Ciudad bucarest = new Ciudad("Bucarest");

        arad.agregarVecino(sibiu, 140);
        arad.agregarVecino(new Ciudad("Timisoara"), 118);
        arad.agregarVecino(new Ciudad("Zerind"), 75);

        sibiu.agregarVecino(arad, 140);
        sibiu.agregarVecino(new Ciudad("Oradea"), 151);
        sibiu.agregarVecino(fagaras, 99);
        sibiu.agregarVecino(new Ciudad("Rimnicu Vilcea"), 80);

        fagaras.agregarVecino(sibiu, 99);
        fagaras.agregarVecino(bucarest, 211);

        List<String> ruta = busqueda(arad, bucarest);
        System.out.println("Ruta más corta: " + ruta);
    }

    private static List<String> busqueda(Ciudad inicio, Ciudad objetivo) {
        Map<Ciudad, Integer> distancias = new HashMap<>();
        Map<Ciudad, Ciudad> anterior = new HashMap<>();
        Set<Ciudad> visitadas = new HashSet<>();
        PriorityQueue<Ciudad> cola = new PriorityQueue<>(Comparator.comparingInt(distancias::get));

        distancias.put(inicio, 0);
        cola.add(inicio);

        while (!cola.isEmpty()) {
            Ciudad actual = cola.poll();

            if (!visitadas.contains(actual)) {
                visitadas.add(actual);

                for (Map.Entry<Ciudad, Integer> vecinoEntry : actual.getVecinos().entrySet()) {
                    Ciudad vecino = vecinoEntry.getKey();
                    int nuevaDistancia = distancias.get(actual) + vecinoEntry.getValue();

                    if (!distancias.containsKey(vecino) || nuevaDistancia < distancias.get(vecino)) {
                        distancias.put(vecino, nuevaDistancia);
                        anterior.put(vecino, actual);
                        cola.add(vecino);
                    }
                }
            }
        }

        return reconstruirRuta(inicio, objetivo, anterior);
    }

    private static List<String> reconstruirRuta(Ciudad inicio, Ciudad objetivo, Map<Ciudad, Ciudad> anterior) {
        List<String> ruta = new ArrayList<>();
        Ciudad actual = objetivo;

        while (actual != null) {
            ruta.add(actual.getNombre());
            actual = anterior.get(actual);
        }

        Collections.reverse(ruta);
        return ruta;
    }
}
