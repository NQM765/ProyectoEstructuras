/*
* Clase que implementa un menú interactivo para un programa de gestión de tarjetas.
* Permite al usuario realizar diversas operaciones como crear tarjetas, ver tarjetas
* almacenadas y salir del programa.
 */
package ProyectoEstructuras;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class menu {

    /*
    * Método principal que inicia la ejecución del programa.
    * Crea un objeto Scanner para leer la entrada del usuario y una lista para
    * almacenar las tarjetas creadas.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Lista<Tarjeta> lista = new Lista<>(); // Crear una instancia de Lista para almacenar las tarjetas
        Lista<Tarjeta> guardadas = new Lista<>();
        int opcion, insertContinue;

        /*
        * Bucle principal que muestra el menú y procesa las opciones del usuario
        * hasta que el usuario elija salir del programa.
         */
        do {
            try {
                System.out.println("Menu:");
                System.out.println("1. Crear tarjeta");
                System.out.println("2. Ver tarjetas");
                System.out.println("3. Ver tarjetas guardadas");
                System.out.println("4. Salir");
                System.out.print("Ingrese el numero de la opcion deseada: ");
                opcion = scanner.nextInt(); // Lee la opción del usuario
                scanner.nextLine(); // Consumir la nueva línea después de leer el número
                System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
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

                            Tarjeta tarjeta = new Tarjeta(tarjetaData, tarjetaTitle, tarjetaTag, null);
                            lista.add(tarjeta);

                            System.out.println("Ingrese '1' para seguir ingresando o ingrese '0' para dejar de ingresar");
                            insertContinue = scanner.nextInt(); // Lee la opción del usuario
                            scanner.nextLine(); // Consumir la nueva línea después de leer el número
                            System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

                        } while (insertContinue != 0);
                        break;

                    case 2:
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
                            System.out.println("Desea guardar esta tarjeta? (Ingrese '1' para guardar, '0' para omitir)");
                            int guardar = scanner.nextInt();
                            scanner.nextLine(); // Consumir la nueva línea después de leer el número

                            if (guardar == 1) {
                                guardadas.add(actualTarjeta);
                                System.out.println("Tarjeta guardada con exito.");
                            }

                            if (iterador.hasNext()) {
                                System.out.println("Ingrese '1' para ver la siguiente tarjeta o ingrese '0' para dejar de ver tarjetas");
                                tarjetaContinue = scanner.nextInt();
                                scanner.nextLine(); // Consumir la nueva línea después de leer el número
                            } else {
                                System.out.println("No hay mas cartas para mostrar");
                                System.out.println("Presione Enter para continuar...");
                                scanner.nextLine(); // Espera a que el usuario presione Enter
                                System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                            }
                        }
                        break;

                    case 3:
                        /*
                        * Opción para ver las tarjetas guardadas.
                        * Muestra las tarjetas almacenadas en la lista al usuario.
                         */
                        Iterator<Tarjeta> iteradorGuardadas = guardadas.iterator();
                        tarjetaContinue = 1;
                        if (!iteradorGuardadas.hasNext()) {
                            System.out.println("No tiene tarjetas guardadas");
                            System.out.println("Presione Enter para continuar...");
                            scanner.nextLine(); // Espera a que el usuario presione Enter
                            System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

                        } else {
                            System.out.println("\u001B[1mTarjetas guardadas:\u001B[0m");
                            while (iteradorGuardadas.hasNext() && tarjetaContinue == 1) {
                                Tarjeta actualTarjeta = iteradorGuardadas.next();
                                System.out.println(actualTarjeta.getTitle());
                                System.out.println(actualTarjeta.getData());
                                System.out.println("Etiqueta: " + actualTarjeta.getTag());
                                if (iteradorGuardadas.hasNext()) {
                                    System.out.println("Ingrese '1' para ver la siguiente tarjeta o ingrese '0' para dejar de ver tarjetas");
                                    tarjetaContinue = scanner.nextInt();
                                    scanner.nextLine(); // Consumir la nueva línea después de leer el número
                                } else {
                                    System.out.println("No hay mas cartas para mostrar");
                                    System.out.println("Presione Enter para continuar...");
                                    scanner.nextLine(); // Espera a que el usuario presione Enter
                                    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                }
                            }
                        }

                        break;

                    case 4:
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

        } while (opcion != 4);

        scanner.close(); // Cerrar el scanner al finalizar su uso
    }
}
