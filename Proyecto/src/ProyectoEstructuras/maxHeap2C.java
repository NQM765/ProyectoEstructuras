/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoEstructuras;

/**
 *
 * @author ISAAC
 */
public class maxHeap2C {
    
    private Tarjeta array[];
    private int maxSize, size;
    
    public maxHeap2C(){
        maxSize = 4;
        size = 0;
        array = new Tarjeta[maxSize];
    }
    
    public int parent(int i){
        return i/2;
    }
    public int leftChild(int i){
        return 2*i;
    }
    public int rightChild(int i){
        return 2*i+1;
    }    
        
    public void insert(Tarjeta item){
        if(size == maxSize){
            growSize();           
        }
        array[size] = item;
        siftUp(size);
        size++;
        //System.out.println("Se ingreso la tarjeta" + item.getTitle());
    }
    
    public void siftUp(int i){
        while(i>0 && array[i].getRating() > array[parent(i)].getRating()){
            Tarjeta item = array[i];
            array[i] = array[parent(i)];
            array[parent(i)] = item;

            i = parent(i);
        }
    }
    
    public void siftDown(int i){
        int minIndex = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if(left < size && array[left].getRating() > array[minIndex].getRating()){
            minIndex = left;
        }
        if(right < size && array[right].getRating() > array[minIndex].getRating()){
            minIndex = right;
        }
        if(i != minIndex){
            Tarjeta item = array[i];
            array[i] = array[minIndex];
            array[minIndex] = item;

            siftDown(minIndex);
        }
    }
    
    public Tarjeta extractMax(){
        if(size == 0){
            return null;
        }
        Tarjeta result = array[0];
        array[0] = array[size-1];
        size--;
        siftDown(0);
        return result;
    }    
    
    public void changePriority(Tarjeta tarjeta){
        int i = search(tarjeta);
        if(i != -1){        
            float p = tarjeta.getRating();
            float oldPri = array[i].getRating();
            
            //System.out.println("las prioridades son:" + " la nueva:" + p + " la vieja: " + oldPri);
            
            array[i].setRating(p);
            if(p>oldPri){
                siftUp(i);
            }
            else{
                siftDown(i);
            }
        }
    }
    
    private int search(Tarjeta tarjeta){
        for(int i=0; i<size; i++){
            if( array[i].getData().equals(tarjeta.getData()) ){
                return i;
            }            
        }
        //System.out.println("La Tarjeta no estaba en el maxHeap");
        return -1;
    }
    
    public void remove(Tarjeta tarjeta){
        int i = search(tarjeta);
        if(i != -1){
            array[i].setRating(6);
            siftUp(i);
            extractMax();
        }
    }
    
    public void growSize(){
        int newMaxSize = 2*maxSize;
        Tarjeta newArray[] = new Tarjeta[newMaxSize];     
        System.arraycopy(array, 0, newArray, 0, maxSize);
        this.array = newArray;
        this.maxSize = newMaxSize;
    }
        
    public boolean isEmpty(){
        return size==0;
    }
    
}
