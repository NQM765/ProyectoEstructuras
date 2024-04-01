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
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class menu {

    private static final String ARCHIVO_TARJETAS = "tarjetas.txt";

    /*
    * Método principal que inicia la ejecución del programa.
    * Crea un objeto Scanner para leer la entrada del usuario y una lista para
    * almacenar las tarjetas creadas.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Lista<Tarjeta> lista = cargarTarjetas(); // Crear una instancia de Lista para almacenar las tarjetas
        Pila<Tarjeta> guardadas = new Pila<>(1000);
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
                System.out.println("5. Salir");
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
                        System.out.println("Ingrese una palabra clave para buscar en el titulo, los datos de las tarjetas o etiquetas:");
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
                                actualTarjeta.addRating(calificacion);
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

        } while (opcion != 5);

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

    private static void imprimirTarjetasGuardadas(Pila<Tarjeta> tarjetas) {
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
}
