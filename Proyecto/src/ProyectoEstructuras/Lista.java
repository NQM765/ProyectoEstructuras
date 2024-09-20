package ProyectoEstructuras;

import java.util.Iterator;

public class Lista<T> implements Iterable<T> {

    private Nodo<T> head;

    private static class Nodo<T> {

        private T data;
        private Nodo<T> next;

        public Nodo(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public Iterator<T> iterator() {
        return new ListaIterator();
    }

    private class ListaIterator implements Iterator<T> {

        private Nodo<T> current;

        public ListaIterator() {
            current = head;
        }

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            T data = current.data;
            current = current.next;
            return data;
        }
    }

    public void add(T data) {
        Nodo<T> newNode = new Nodo<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Nodo<T> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public void display() {
        Nodo<T> temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public void remove(T dataToRemove) {
        if (head == null) {
            return; // La lista está vacía, no hay nada que eliminar
        }

        // Si el elemento a eliminar es el primer nodo
        if (head.data.equals(dataToRemove)) {
            head = head.next;
            return;
        }

        Nodo<T> prev = null;
        Nodo<T> current = head;

        // Buscamos el nodo a eliminar
        while (current != null && !current.data.equals(dataToRemove)) {
            prev = current;
            current = current.next;
        }

        // Si no se encontró el nodo a eliminar
        if (current == null) {
            return;
        }

        // Enlazamos el nodo previo con el siguiente, eliminando el nodo actual
        prev.next = current.next;
    }

}
