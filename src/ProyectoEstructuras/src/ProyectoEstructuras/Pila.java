package ProyectoEstructuras;

import java.util.Iterator;

public class Pila<T> implements Iterable<T>{
    T [] p; 
    int top;
    
    public Iterator<T> iterator() {
        return new pilaIterator();
    }

    private class pilaIterator implements Iterator<T> {
        private int current;
        
        public pilaIterator() {
            current = top;
        }
        
        public boolean hasNext() {
            return current != 0;
        }
        
        public T next() {
            
            T data = p[current-1];
            current--;
            return data;
        }
    }    
    
    public Pila(int lenght){  
        p = (T[]) new Object[lenght]; 
        top=0;    
    }
    public void add(T x){  
        p[top++] = x;  
    }
    public T display(){ 
        return p[--top];
    }
    public boolean isEmpty(){ 
        return top<=0;
    }
    public boolean isFull(){ 
        return top>=p.length;
    }
}
