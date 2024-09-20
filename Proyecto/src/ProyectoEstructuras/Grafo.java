package ProyectoEstructuras;

import java.util.*;

public class Grafo<T> {

    private Map<T, List<T>> adjList = new HashMap<>();

    // Añadir un nodo al grafo
    public void agregarNodo(T nodo) {
        adjList.putIfAbsent(nodo, new ArrayList<>());
    }

    // Añadir una arista entre dos nodos
    public void agregarArista(T nodo1, T nodo2) {
        // Asegurarse de que ambos nodos existan en la lista de adyacencia
        adjList.putIfAbsent(nodo1, new ArrayList<>());
        adjList.putIfAbsent(nodo2, new ArrayList<>());

        // Ahora agregar la arista
        adjList.get(nodo1).add(nodo2);
        adjList.get(nodo2).add(nodo1);
    }

    // Obtener las tarjetas relacionadas con un nodo (vecinos)
    public List<T> obtenerRelacionados(T nodo) {
        return adjList.get(nodo);
    }

    // Visualizar todas las conexiones del grafo
    public void mostrarGrafo() {
        for (T nodo : adjList.keySet()) {
            System.out.println("Tarjeta: " + nodo.toString() + " -> " + adjList.get(nodo));
        }
    }

    public void mostrarRelacionesSimplificadas() {
        for (T nodo : adjList.keySet()) {
            System.out.println("Tarjeta: " + ((Tarjeta) nodo).resumen());
            System.out.println("Relacionada con:");
            for (T adyacente : adjList.get(nodo)) {
                System.out.println("  - " + ((Tarjeta) adyacente).resumen());
            }
        }
    }

}
