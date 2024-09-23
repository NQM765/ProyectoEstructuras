/*
* Clase que implementa un menú interactivo para un programa de gestión de tarjetas.
* Permite al usuario realizar diversas operaciones como crear tarjetas, ver tarjetas
* almacenadas y salir del programa.
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
import java.util.List;

public class menu {

    private static Grafo<Tarjeta> grafoTarjetas = new Grafo<>();
    private static final String ARCHIVO_TARJETAS = "tarjetas.txt";

    /*
    * Método principal que inicia la ejecución del programa.
    * Crea un objeto Scanner para leer la entrada del usuario y una lista para
    * almacenar las tarjetas creadas.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Lista<Tarjeta> lista = cargarTarjetas(); // Crear una instancia de Lista para almacenar las tarjetas
        Lista<Tarjeta> guardadas = new Lista<>();
        ConjuntoDisyunto<Tarjeta> conjuntoDisyunto = new ConjuntoDisyunto<>();
        AVLTree avl = new AVLTree();
        for (Tarjeta tarjeta : lista) {
            conjuntoDisyunto.makeSet(tarjeta);
            avl.insert(tarjeta);
        }

        maxHeap2C heap = cargarTarjetasHeap();

        int opcion, insertContinue;

        /*
        * Bucle principal que muestra el menú y procesa las opciones del usuario
        * hasta que el usuario elija salir del programa.
         */
        do {
            try {
                System.out.println("Menu:");
                System.out.println("1. Crear tarjeta");
                System.out.println("2. Buscar una tarjeta y/o realizar cambios o eliminarla");
                System.out.println("3. Ver tarjetas");
                System.out.println("4. Ver tarjetas guardadas");
                System.out.println("5. Agrupar tarjetas por etiqueta");
                System.out.println("6. Ver tarjetas mejor calificadas");
                System.out.println("7. Ver las tarjetas más recientes");
                System.out.println("8. Mostrar relaciones entre tarjetas (grafo)");
                System.out.println("9. Buscar una tarjeta (Hash) y/o realizar cambios o eliminarla");
                System.out.println("10. Salir");
                System.out.print("Ingrese el numero de la opcion deseada: ");
                opcion = scanner.nextInt(); // Lee la opción del usuario
                scanner.nextLine(); // Consumir la nueva línea después de leer el número
                System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                switch (opcion) {
                    case 1:
                        /*
                        * Submenú para crear tarjetas.
                        * Permite al usuario ingresar información para crear nuevas tarjetas
                        * y almacenarlas en la lista.
                         */
                        do {
                            System.out.println("\u001B[1mCreacion del consejo\u001B[0m");
                            System.out.println("Ingrese el titulo del consejo");
                            String tarjetaTitle = scanner.nextLine();

                            System.out.println("Ingrese el consejo");
                            String tarjetaData = scanner.nextLine();

                            System.out.println("Ingrese la etiqueta del consejo");
                            String tarjetaTag = scanner.nextLine();

                            Tarjeta tarjeta = new Tarjeta(tarjetaData, tarjetaTitle, tarjetaTag);
                            lista.add(tarjeta);
                            heap.insert(tarjeta);
                            avl.insert(tarjeta);

                            System.out.println("Ingrese '1' para seguir ingresando o ingrese '0' para dejar de ingresar");
                            insertContinue = scanner.nextInt(); // Lee la opción del usuario
                            scanner.nextLine(); // Consumir la nueva línea después de leer el número
                            System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

                        } while (insertContinue != 0);
                        break;
                    case 2:
                        /*
                            * Opción para buscar y hacer cambios a una tarjeta una tarjeta.
                            * Permite al usuario ingresar una palabra clave para buscar en el título o los datos de las tarjetas.
                            * Muestra la tarjeta encontrada si se encuentra alguna coincidencia.
                         */
                        System.out.println("\u001B[1mBuscar una tarjeta\u001B[0m");
                        System.out.println("Ingrese una palabra clave para buscar en el titulo o en los datos de las tarjetas");
                        String keyword = scanner.nextLine().toLowerCase(); // Convertir la palabra clave a minúsculas para realizar una comparación sin distinción entre mayúsculas y minúsculas

                        boolean found = false; // Variable para rastrear si se encontraron tarjetas que coinciden con la palabra clave

                        Iterator<Tarjeta> iteradorBuscar = lista.iterator();
                        while (iteradorBuscar.hasNext()) {
                            Tarjeta actualTarjeta = iteradorBuscar.next();
                            // Compara la palabra clave con el título, los datos de la tarjeta y la etiqueta, ignorando las mayúsculas y minúsculas
                            if (actualTarjeta.getTitle().toLowerCase().contains(keyword) || actualTarjeta.getData().toLowerCase().contains(keyword) || actualTarjeta.getTag().contains(keyword)) {
                                System.out.println("\u001B[1mTarjeta encontrada:\u001B[0m");
                                System.out.println(actualTarjeta.getTitle());
                                System.out.println(actualTarjeta.getData());
                                System.out.println("Etiqueta: " + actualTarjeta.getTag());
                                System.out.println("Calificación promedio: " + actualTarjeta.getRating() + " estrellas");

                                found = true; // Se encontró al menos una tarjeta que coincide con la palabra clave
                            }
                            if (found) {
                                // Si se encontró una tarjeta, ofrece opciones adicionales
                                System.out.println("Seleccione una opcion:");
                                System.out.println("1. Editar tarjeta");
                                System.out.println("2. Eliminar tarjeta");
                                System.out.println("3. Siguiente busqueda o salida");

                                int opcionEditarEliminar = scanner.nextInt();
                                scanner.nextLine(); // Consumir la nueva línea después de leer la opción

                                switch (opcionEditarEliminar) {
                                    case 1:
                                        // Opción para editar la tarjeta encontrada
                                        System.out.println("Ingrese el nuevo titulo de la tarjeta:");
                                        String nuevoTitulo = scanner.nextLine();
                                        System.out.println("Ingrese el nuevo consejo de la tarjeta:");
                                        String nuevoConsejo = scanner.nextLine();
                                        System.out.println("Ingrese la nueva etiqueta de la tarjeta:");
                                        String nuevaEtiqueta = scanner.nextLine();

                                        // Actualizar los valores de la tarjeta encontrada
                                        actualTarjeta.setTitle(nuevoTitulo);
                                        actualTarjeta.setData(nuevoConsejo);
                                        actualTarjeta.setTag(nuevaEtiqueta);

                                        System.out.println("Tarjeta actualizada correctamente.");
                                        System.out.println("Presione Enter para continuar...");
                                        scanner.nextLine(); // Espera a que el usuario presione Enter
                                        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

                                        break;
                                    case 2:
                                        // Opción para eliminar la tarjeta encontrada
                                        lista.remove(actualTarjeta);
                                        heap.remove(actualTarjeta);
                                        System.out.println("Tarjeta eliminada correctamente.");
                                        System.out.println("Presione Enter para continuar...");
                                        scanner.nextLine(); // Espera a que el usuario presione Enter
                                        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                        break;
                                    case 3:
                                        break;
                                    default:
                                        System.out.println("Opción no válida.");

                                }
                            } else {
                                System.out.println("No se encontraron tarjetas que coincidan con la palabra clave.");
                                System.out.println("Presione Enter para continuar...");
                                scanner.nextLine(); // Espera a que el usuario presione Enter
                                System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

                            }
                        }

                        break;

                    case 3:
                        /*
                        * Opción para ver tarjetas.
                        * Muestra las tarjetas almacenadas en la lista al usuario.
                         */

                        Iterator<Tarjeta> iterador = lista.iterator();

                        int tarjetaContinue = 1;
                        System.out.println("\u001B[1mTarjetas en la lista\u001B[0m");
                        while (iterador.hasNext() && tarjetaContinue == 1) {
                            Tarjeta actualTarjeta = iterador.next();

                            System.out.println(actualTarjeta.getTitle());
                            System.out.println(actualTarjeta.getData());
                            System.out.println("Etiqueta: " + actualTarjeta.getTag());
                            System.out.println("Calificacion promedio: " + actualTarjeta.getRating() + " estrellas");
                            System.out.println("Desea guardar esta tarjeta? (Ingrese '1' para guardar, '0' para omitir)");
                            int guardar = scanner.nextInt();
                            scanner.nextLine(); // Consumir la nueva línea después de leer el número
                            if (guardar == 1) {
                                guardadas.add(actualTarjeta);
                                System.out.println("Tarjeta guardada con exito.");
                            }
                            System.out.println("Desea calificar la tarjeta?  (Ingrese '1' para calificar, '0' para omitir)");
                            int calificar = scanner.nextInt();
                            scanner.nextLine();
                            if (calificar == 1) {
                                System.out.println("Ingrese la calificacion para la tarjeta (1-5):");
                                int calificacion = scanner.nextInt();
                                scanner.nextLine();
                                actualTarjeta.setRating(actualTarjeta.addRating(calificacion));
                                heap.changePriority(actualTarjeta);
                                System.out.println("Tarjeta calificada con exito!");
                            }

                            if (iterador.hasNext()) {
                                System.out.println("Ingrese '1' para ver la siguiente tarjeta o ingrese '0' para dejar de ver tarjetas");
                                tarjetaContinue = scanner.nextInt();
                                scanner.nextLine(); // Consumir la nueva línea después de leer el número
                            } else {
                                System.out.println("No hay mas cartas para mostrar");
                                System.out.println("Presione Enter para continuar...");
                                scanner.nextLine(); // Espera a que el usuario presione Enter
                            }
                        }

                        break;

                    case 4:
                        /*
                        * Opción para ver las tarjetas guardadas.
                        * Muestra las tarjetas almacenadas en la lista al usuario.
                         */
                        Iterator<Tarjeta> iteradorGuardadas = guardadas.iterator();
                        if (!iteradorGuardadas.hasNext()) {
                            System.out.println("No tiene tarjetas guardadas");
                            System.out.println("Presione Enter para continuar...");
                            scanner.nextLine(); // Espera a que el usuario presione Enter
                            System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

                        } else {
                            System.out.println("\u001B[1mTarjetas guardadas\u001B[0m");
                            imprimirTarjetasGuardadas(guardadas);
                        }
                        break;
                    case 5:
                        System.out.println("Agrupando tarjetas por etiquetas...");
                        agruparTarjetasPorEtiqueta(lista, conjuntoDisyunto);
                        System.out.println("Tarjetas agrupadas con éxito.");
                        break;

                    case 6:
                        /*
                        * Opcion de ver las tarjetas con mayor calificacion
                        * Muestra las tarjetas almacenadas en el heap al usuario
                         */

                        int tarjetaHeapContinue = 1;

                        while (tarjetaHeapContinue == 1 && !heap.isEmpty()) {
                            Tarjeta actualHeapTarjeta = heap.extractMax();

                            System.out.println(actualHeapTarjeta.getTitle());
                            System.out.println(actualHeapTarjeta.getData());
                            System.out.println("Etiqueta: " + actualHeapTarjeta.getTag());
                            System.out.println("Calificacion promedio: " + actualHeapTarjeta.getRating() + " estrellas");

                            if (!heap.isEmpty()) {
                                System.out.println("Ingrese '1' para ver la siguiente tarjeta o ingrese '0' para dejar de ver tarjetas");
                                tarjetaHeapContinue = scanner.nextInt();
                                scanner.nextLine(); // Consumir la nueva línea después de leer el número
                            } else {
                                System.out.println("No hay mas cartas para mostrar");
                                System.out.println("Presione Enter para continuar...");
                                scanner.nextLine(); // Espera a que el usuario presione Enter                                                                
                            }

                        }

                        heap = cargarTarjetasHeap(); //volver a llenar el heap

                        break;

                    case 7:
                        /*
                        * Opcion de ver las tarjetas mas recientes
                        * Muestra las tarjetas almacenadas en la lista al usuario
                         */
                        List<Tarjeta> tarjetas = avl.getTarjetasInDescendingOrder();
                        for (Tarjeta tarjeta : tarjetas) {
                            System.out.println(tarjeta.getTitle());
                            System.out.println(tarjeta.getData());
                            System.out.println("Etiqueta: " + tarjeta.getTag());
                            System.out.println("Calificacion promedio: " + tarjeta.getRating() + " estrellas");
                            System.out.println("Fecha de creacion: " + tarjeta.getTime());
                        }
                        System.out.println("Presione Enter para continuar...");
                        scanner.nextLine(); // Espera a que el usuario presione Enter
                        break;
                    case 8:
                        System.out.println("Ingrese la etiqueta por la que quiere ver las relaciones:");
                        String etiquetaIngresada = scanner.nextLine();
                        mostrarRelacionesPorEtiqueta(etiquetaIngresada, lista, conjuntoDisyunto);
                        break;
                        
                    case 9:

                        System.out.println("\u001B[1mBuscar una tarjeta\u001B[0m");
                        System.out.println("Ingrese una palabra clave para buscar en el título o en los datos de las tarjetas");
                        String keywordHash = scanner.nextLine().toLowerCase(); // Convertir la palabra clave a minúsculas

                        boolean foundHash = false; // Variable para rastrear si se encontraron tarjetas

                        Iterator<Tarjeta> iteradorBuscarHash = lista.iterator();
                        hash hashstr = new hash();

                        while (iteradorBuscarHash.hasNext()) {
                            Tarjeta actualTarjeta = iteradorBuscarHash.next();

                            // Compara la palabra clave con el título, los datos de la tarjeta y la etiqueta
                            if (hashstr.search(keywordHash, actualTarjeta.getTitle().toLowerCase()) || 
                                hashstr.search(keywordHash, actualTarjeta.getData().toLowerCase()) || 
                                hashstr.search(keywordHash, actualTarjeta.getTag().toLowerCase())) {

                                System.out.println("\u001B[1mTarjeta encontrada:\u001B[0m");
                                System.out.println(actualTarjeta.getTitle());
                                System.out.println(actualTarjeta.getData());
                                System.out.println("Etiqueta: " + actualTarjeta.getTag());
                                System.out.println("Calificación promedio: " + actualTarjeta.getRating() + " estrellas");

                                foundHash = true; // Se encontró al menos una tarjeta

                                // Opciones adicionales
                                System.out.println("Seleccione una opción:");
                                System.out.println("1. Editar tarjeta");
                                System.out.println("2. Eliminar tarjeta");
                                System.out.println("3. Siguiente búsqueda o salida");

                                int opcionEditarEliminar = scanner.nextInt();
                                scanner.nextLine(); // Consumir la nueva línea

                                switch (opcionEditarEliminar) {
                                    case 1:
                                        System.out.println("Ingrese el nuevo título de la tarjeta:");
                                        String nuevoTitulo = scanner.nextLine();
                                        System.out.println("Ingrese el nuevo consejo de la tarjeta:");
                                        String nuevoConsejo = scanner.nextLine();
                                        System.out.println("Ingrese la nueva etiqueta de la tarjeta:");
                                        String nuevaEtiqueta = scanner.nextLine();

                                        actualTarjeta.setTitle(nuevoTitulo);
                                        actualTarjeta.setData(nuevoConsejo);
                                        actualTarjeta.setTag(nuevaEtiqueta);

                                        System.out.println("Tarjeta actualizada correctamente.");
                                        System.out.println("Presione Enter para continuar...");
                                        scanner.nextLine(); // Espera a que el usuario presione Enter
                                        break;
                                    case 2:
                                        lista.remove(actualTarjeta);
                                        heap.remove(actualTarjeta);
                                        System.out.println("Tarjeta eliminada correctamente.");
                                        System.out.println("Presione Enter para continuar...");
                                        scanner.nextLine(); // Espera a que el usuario presione Enter
                                        break;
                                    case 3:
                                        // Continuar con la siguiente búsqueda
                                        break;
                                    default:
                                        System.out.println("Opción no válida.");
                                }
                            }
                        }

                        if (!foundHash) {
                            System.out.println("No se encontraron tarjetas que coincidan con la palabra clave.");
                            System.out.println("Presione Enter para continuar...");
                            scanner.nextLine(); // Espera a que el usuario presione Enter
                        }

                        break;                    

                    case 10:
                        guardarTarjetas(lista);
                        break;
                    default:
                        System.out.println("Opcion no valida. Por favor, ingrese un numero valido.");
                }
            } catch (InputMismatchException e) {
                /*
                * Manejo de excepción para asegurar que el programa no se bloquee
                * si el usuario ingresa un valor no válido.
                 */
                System.out.println("Error: Debe ingresar un numero entero.");
                scanner.nextLine(); // Limpiar el buffer del scanner
                opcion = 0; // Establecer opción en 0 para repetir el ciclo
            }

        } while (opcion
                != 9);

        scanner.close(); // Cerrar el scanner al finalizar su uso
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

    private static maxHeap2C cargarTarjetasHeap() {
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
    // Método para construir el grafo basado en etiquetas

    // Método para mostrar relaciones por una etiqueta dada
    public static void mostrarRelacionesPorEtiqueta(String etiqueta, Lista<Tarjeta> lista, ConjuntoDisyunto<Tarjeta> conjuntoDisyunto) {
        Grafo<Tarjeta> grafo = new Grafo<>();

        // Construimos el grafo solo con las tarjetas que tienen la etiqueta dada
        Iterator<Tarjeta> iterador = lista.iterator();
        while (iterador.hasNext()) {
            Tarjeta tarjeta = iterador.next();
            if (tarjeta.getTag().equalsIgnoreCase(etiqueta)) {
                // Agregar nodos y relaciones al grafo
                for (Tarjeta otraTarjeta : lista) {
                    if (!tarjeta.equals(otraTarjeta) && otraTarjeta.getTag().equalsIgnoreCase(etiqueta)) {
                        grafo.agregarArista(tarjeta, otraTarjeta);
                    }
                }
            }
        }

        // Mostrar las relaciones entre tarjetas con la etiqueta dada
        System.out.println("Mostrando relaciones entre tarjetas con la etiqueta: " + etiqueta);
        grafo.mostrarRelacionesSimplificadas();
    }

}
