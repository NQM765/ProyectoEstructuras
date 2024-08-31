package ProyectoEstructuras;

public class maxHeap3C {
    private Tarjeta array[];
    private int maxSize, size;
    
    public maxHeap3C(){
        maxSize = 4;
        size = 0;
        array = new Tarjeta[maxSize];
    }
    
    public int parent(int i){
        return i/3;
    }
    public int leftChild(int i){
        return 3*i;
    }
    public int centralChild(int i){
        return 3*i+1;
    }
    public int rightChild(int i){
        return 3*i+2;
    }    
        
    public void insert(Tarjeta item){
        if(size == maxSize){
            growSize();           
        }
        array[size] = item;
        siftUp(size);
        size++;        
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
        int central = centralChild(i);
        int right = rightChild(i);
        
        if(left < size && array[left].getRating() > array[minIndex].getRating()){
            minIndex = left;
        }
        if(central < size && array[central].getRating() > array[minIndex].getRating()){
            minIndex = central;
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
            if(array[i].getData().equals(tarjeta.getData())){
                return i;
            }            
        }
        System.out.println("La Tarjeta no estaba en el maxHeap");
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
          
}
