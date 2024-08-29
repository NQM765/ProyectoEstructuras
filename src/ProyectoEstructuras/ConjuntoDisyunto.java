package ProyectoEstructuras;

import java.util.HashMap;
import java.util.Map;

public class ConjuntoDisyunto<T> {
    private Map<T, T> parent = new HashMap<>();
    private Map<T, Integer> rank = new HashMap<>();

    // Inicializa un nuevo conjunto con un solo elemento.
    public void makeSet(T item) {
        parent.put(item, item);
        rank.put(item, 0);
    }

    // Encuentra el representante del conjunto al que pertenece el elemento.
    public T find(T item) {
        if (!parent.containsKey(item)) {
            return null;
        }
        if (parent.get(item) != item) {
            parent.put(item, find(parent.get(item))); // Compresión de caminos
        }
        return parent.get(item);
    }

    // Une dos conjuntos disjuntos.
    public void union(T item1, T item2) {
        T root1 = find(item1);
        T root2 = find(item2);

        if (root1 == null || root2 == null || root1.equals(root2)) {
            return;
        }

        int rank1 = rank.get(root1);
        int rank2 = rank.get(root2);

        if (rank1 < rank2) {
            parent.put(root1, root2);
        } else if (rank1 > rank2) {
            parent.put(root2, root1);
        } else {
            parent.put(root2, root1);
            rank.put(root1, rank1 + 1);
        }
    }
}
