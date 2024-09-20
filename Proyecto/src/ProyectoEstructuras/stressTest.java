/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ProyectoEstructuras;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Nicolas
 */
public class stressTest {

    /**
     * @param args the command line arguments
     */
    private static final String ARCHIVO_TARJETAS = "tarjetas.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Cargar tarjetas
        Lista<Tarjeta> lista = cargarTarjetas();

        // Prueba sin compresión de caminos
        System.out.println("Prueba sin compresión de caminos:");
        long startTime = System.currentTimeMillis();
        ConjuntoDisyuntoSinCompresion<Tarjeta> conjuntoDisyuntoSinCompresion = new ConjuntoDisyuntoSinCompresion<>();
        for (Tarjeta tarjeta : lista) {
            conjuntoDisyuntoSinCompresion.makeSet(tarjeta);
        }
        agruparTarjetasPorEtiquetaSinCompresion(lista, conjuntoDisyuntoSinCompresion);
        long endTime = System.currentTimeMillis();
        System.out.println("Tiempo sin compresión de caminos: " + (endTime - startTime) + " ms");

        // Prueba con compresión de caminos
        System.out.println("\n ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Prueba con compresión de caminos:");
        startTime = System.currentTimeMillis();
        ConjuntoDisyunto<Tarjeta> conjuntoDisyuntoConCompresion = new ConjuntoDisyunto<>();
        for (Tarjeta tarjeta : lista) {
            conjuntoDisyuntoConCompresion.makeSet(tarjeta);
        }
        agruparTarjetasPorEtiqueta(lista, conjuntoDisyuntoConCompresion);
        endTime = System.currentTimeMillis();
        System.out.println("Tiempo con compresión de caminos: " + (endTime - startTime) + " ms");

        scanner.close();
    }

    private static Lista<Tarjeta> cargarTarjetas() {
        Lista<Tarjeta> lista = new Lista<>();
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
                lista.add(tarjeta);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar las tarjetas: " + e.getMessage());
        }
        return lista;
    }

    private static void guardarTarjetas(Lista<Tarjeta> tarjetas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_TARJETAS))) {
            Iterator<Tarjeta> iterador = tarjetas.iterator();
            while (iterador.hasNext()) {
                Tarjeta tarjeta = iterador.next();
                bw.write(tarjeta.getTitle() + "," + tarjeta.getData() + "," + tarjeta.getTag() + "," + tarjeta.getRating());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar las tarjetas: " + e.getMessage());
        }
    }

    private static void imprimirTarjetasGuardadas(Lista<Tarjeta> tarjetas) {
        Iterator<Tarjeta> iterador = tarjetas.iterator();
        int tarjetaContinue = 1;
        Scanner scanner = new Scanner(System.in);
        while (iterador.hasNext() && tarjetaContinue == 1) {

            Tarjeta actualTarjeta = iterador.next();
            System.out.println(actualTarjeta.getTitle());
            System.out.println(actualTarjeta.getData());
            System.out.println("Etiqueta: " + actualTarjeta.getTag());
            if (iterador.hasNext()) {
                System.out.println("Ingrese '1' para ver la siguiente tarjeta o ingrese '0' para dejar de ver tarjetas");
                tarjetaContinue = scanner.nextInt(); // Consumir la nueva línea después de leer el número
                scanner.nextLine();
            } else {
                System.out.println("No hay mas cartas para mostrar");
                System.out.println("Presione Enter para continuar...");
                scanner.nextLine();
                System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

            }
        }

    }

    private static void agruparTarjetasPorEtiqueta(Lista<Tarjeta> lista, ConjuntoDisyunto<Tarjeta> conjuntoDisyunto) {
        // Union de tarjetas que comparten la misma etiqueta
        for (Tarjeta tarjeta1 : lista) {
            for (Tarjeta tarjeta2 : lista) {
                if (!tarjeta1.equals(tarjeta2) && tarjeta1.getTag().equals(tarjeta2.getTag())) {
                    conjuntoDisyunto.union(tarjeta1, tarjeta2);
                }
            }
        }

        // Crear grupos basados en la etiqueta de la raíz
        Map<String, Lista<Tarjeta>> grupos = new HashMap<>();
        for (Tarjeta tarjeta : lista) {
            String rootTag = conjuntoDisyunto.find(tarjeta).getTag();
            grupos.putIfAbsent(rootTag, new Lista<>());
            grupos.get(rootTag).add(tarjeta);
        }

        // Mostrar las tarjetas agrupadas
        for (Map.Entry<String, Lista<Tarjeta>> entry : grupos.entrySet()) {
            System.out.println("Grupo de tarjetas con etiqueta '" + entry.getKey() + "':");
            for (Tarjeta tarjeta : entry.getValue()) {
                System.out.println(tarjeta);  // Aquí se usa el método toString() de la clase Tarjeta
            }
        }
    }

    private static void agruparTarjetasPorEtiquetaSinCompresion(Lista<Tarjeta> lista, ConjuntoDisyuntoSinCompresion<Tarjeta> conjuntoDisyuntoSinCompresion) {
        // Union de tarjetas que comparten la misma etiqueta
        for (Tarjeta tarjeta1 : lista) {
            for (Tarjeta tarjeta2 : lista) {
                if (!tarjeta1.equals(tarjeta2) && tarjeta1.getTag().equals(tarjeta2.getTag())) {
                    conjuntoDisyuntoSinCompresion.union(tarjeta1, tarjeta2);
                }
            }
        }

        // Crear grupos basados en la etiqueta de la raíz
        Map<String, Lista<Tarjeta>> grupos = new HashMap<>();
        for (Tarjeta tarjeta : lista) {
            String rootTag = conjuntoDisyuntoSinCompresion.find(tarjeta).getTag();
            grupos.putIfAbsent(rootTag, new Lista<>());
            grupos.get(rootTag).add(tarjeta);
        }

        // Mostrar las tarjetas agrupadas
        for (Map.Entry<String, Lista<Tarjeta>> entry : grupos.entrySet()) {
            System.out.println("Grupo de tarjetas con etiqueta '" + entry.getKey() + "':");
            for (Tarjeta tarjeta : entry.getValue()) {
                System.out.println(tarjeta);  // Aquí se usa el método toString() de la clase Tarjeta
            }
        }
    }

}
