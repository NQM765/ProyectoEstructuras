/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoEstructuras;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author ISAAC
 */

public class stressTestMaxHeap {

    /**
     * @param args the command line arguments
     */
    private static final String ARCHIVO_TARJETAS = "tarjetas.txt";

    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
       
        //Prueba maxHeap con 2 hijos
        
        System.out.println("Prueba maxHeap con 2 hijos:");
        
        long startTime = System.currentTimeMillis();
        
        maxHeap2C heap2C = cargarTarjetasHeap2C();      // Cargar tarjetas a los maxHeaps
        
        while(!heap2C.isEmpty()){
            heap2C.extractMax();
        }

        long endTime = System.currentTimeMillis();        
        
        System.out.println("Tiempo maxHeap con 2 hijos: " + (endTime - startTime) + " ms");
        
        System.out.println("\n ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");        
        
        //Prueba maxHeap con 3 hijos
        
        System.out.println("Prueba maxHeap con 3 hijos:");
        
        startTime = System.currentTimeMillis();
        
        maxHeap3C heap3C = cargarTarjetasHeap3C();      // Cargar tarjetas a los maxHeaps
        
        while(!heap3C.isEmpty()){
            heap3C.extractMax();
        }

        endTime = System.currentTimeMillis();        
        
        System.out.println("Tiempo maxHeap con 3 hijos: " + (endTime - startTime) + " ms");        

        //scanner.close();
    }

    private static maxHeap2C cargarTarjetasHeap2C() {
        maxHeap2C heap = new maxHeap2C();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_TARJETAS))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String title = parts[0];
                String data = parts[1];
                String tag = parts[2];
                float rating = Float.parseFloat(parts[3]);
                Tarjeta tarjeta = new Tarjeta(data, title, tag, rating);
                tarjeta.ratings.add(rating);
                heap.insert(tarjeta);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar las tarjetas al heap: " + e.getMessage());
        }
        return heap;
    }   
    
    private static maxHeap3C cargarTarjetasHeap3C() {
        maxHeap3C heap = new maxHeap3C();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_TARJETAS))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String title = parts[0];
                String data = parts[1];
                String tag = parts[2];
                float rating = Float.parseFloat(parts[3]);
                Tarjeta tarjeta = new Tarjeta(data, title, tag, rating);
                tarjeta.ratings.add(rating);
                heap.insert(tarjeta);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar las tarjetas al heap: " + e.getMessage());
        }
        return heap;
    }      

}
