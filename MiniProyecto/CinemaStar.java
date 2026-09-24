package MiniProyecto;

import java.util.Scanner;

public class CinemaStar 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        Pelicula[] peliculasRegistradas = new Pelicula[20];
        int cantidadPeliculas = 0;

        // Arreglo de las 3 salas del teatro
        Sala[] salas = new Sala[3];
        salas[0] = new Sala(1);
        salas[1] = new Sala(2);
        salas[2] = new Sala(3);

        // Se usa do-while porque el menu principal debe mostrarse SI O SI al
        // menos una vez; luego se repite hasta que el usuario elija Salir (4).
        int opcion = 0;
        do {
            System.out.println("\n========= CINEMASTAR =========");
            System.out.println("1. Crear o Ver Peliculas");
            System.out.println("2. Asignar Funciones");
            System.out.println("3. Modulo de Ventas");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");
            
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    cantidadPeliculas = menuPeliculas(scanner, peliculasRegistradas, cantidadPeliculas);
                    break;
                case 2:
                    menuFunciones(scanner, salas, peliculasRegistradas, cantidadPeliculas);
                    break;
                case 3:
                    menuVentas(scanner, salas);
                    break;
                case 4:
                    System.out.println("\nGracias por usar el sistema CinemaStar. Hasta pronto!");
                    break;
                default:
                    System.out.println("\nOpcion invalida, intente de nuevo.");
            }
        } while (opcion != 4);

        scanner.close();
    }

    
    // ==================================================================
    // MENU 1: PELICULAS
    // (la captura de datos vive en Pelicula.solicitarDatos)
    // ==================================================================
    private static int menuPeliculas(Scanner scanner, Pelicula[] peliculasRegistradas, int cantidadPeliculas) {
        // Se usa do-while porque el submenu debe mostrarse al menos una vez;
        // luego se repite hasta que el usuario elija Volver (3).
        int opcionSubmenu;
        do {
            System.out.println("\n--- Menu de Peliculas ---");
            System.out.println("1. Registrar Pelicula");
            System.out.println("2. Ver Peliculas");
            System.out.println("3. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");
            opcionSubmenu = scanner.nextInt();

            switch (opcionSubmenu) {
                case 1:
                    if (cantidadPeliculas >= peliculasRegistradas.length) {
                        System.out.println("\nNo hay espacio para registrar mas peliculas.");
                    } else {
                        peliculasRegistradas[cantidadPeliculas] = Pelicula.solicitarDatos(scanner);
                        cantidadPeliculas = cantidadPeliculas + 1;
                        System.out.println("\nPelicula registrada con exito.");
                    }
                    break;
                case 2:
                    mostrarListaPeliculas(peliculasRegistradas, cantidadPeliculas);
                    break;
                case 3:
                    break;
                default:
                    System.out.println("\nOpcion invalida, intente de nuevo.");
            }
        } while (opcionSubmenu != 3);

        return cantidadPeliculas;
    }

    private static void mostrarListaPeliculas(Pelicula[] peliculasRegistradas, int cantidadPeliculas) {
        if (cantidadPeliculas == 0) {
            System.out.println("\nNo hay peliculas registradas todavia.");
        } else {
            System.out.println("\n--- Peliculas Registradas ---");
            for (int indice = 0; indice < cantidadPeliculas; indice = indice + 1) {
                System.out.print((indice + 1) + ". ");
                peliculasRegistradas[indice].mostrarInfo();
            }
        }
    }

    // ==================================================================
    // MENU 2: ASIGNACION DE FUNCIONES
    // (la validacion de tipo y la asignacion viven en Sala.asignarPelicula)
    // ==================================================================
    private static void menuFunciones(Scanner scanner, Sala[] salas, Pelicula[] peliculasRegistradas, int cantidadPeliculas) {
        if (cantidadPeliculas == 0) {
            System.out.println("\n***Primero debe registrar al menos una pelicula (Opcion 1 del menu principal).");
            return;
        }

        // Se usa do-while porque el submenu debe mostrarse al menos una vez;
        // luego se repite hasta que el usuario elija Volver (3).
        int opcionSubmenu;
        do {
            System.out.println("\n--- Asignacion de Funciones ---");
            System.out.println("1. Asignar pelicula a una sala/franja");
            System.out.println("2. Ver funciones asignadas");
            System.out.println("3. Volver al menu principal");
            System.out.print("\nSeleccione una opcion: ");
            opcionSubmenu = scanner.nextInt();

            switch (opcionSubmenu) {
                case 1:
                    asignarPeliculaASala(scanner, salas, peliculasRegistradas, cantidadPeliculas);
                    break;
                case 2:
                    for (int indiceSala = 0; indiceSala < salas.length; indiceSala = indiceSala + 1) {
                        salas[indiceSala].mostrarFunciones();
                    }
                    break;
                case 3:
                    break;
                default:
                    System.out.println("\nOpcion invalida, intente de nuevo.");
            }
        } while (opcionSubmenu != 3);
    }

    private static void asignarPeliculaASala(Scanner scanner, Sala[] salas, Pelicula[] peliculasRegistradas, int cantidadPeliculas) {
        System.out.print("\nIngrese el numero de sala (1, 2 o 3): ");
        int idSala = scanner.nextInt();

        if (idSala < 1 || idSala > 3) {
            System.out.println("\n***Sala invalida.***");
            return;
        }

        mostrarListaPeliculas(peliculasRegistradas, cantidadPeliculas);
        System.out.print("\nSeleccione el numero de la pelicula: ");
        int idPelicula = scanner.nextInt();

        if (idPelicula < 1 || idPelicula > cantidadPeliculas) {
            System.out.println("\n***Pelicula invalida.***");
            return;
        }

        System.out.println("\nFranjas horarias disponibles:");
        System.out.println("1. 14:00 - 16:30");
        System.out.println("2. 16:30 - 19:00");
        System.out.println("3. 19:00 - 21:00");
        System.out.print("Seleccione la franja: ");
        int franjaSeleccionada = scanner.nextInt();

        if (franjaSeleccionada < 1 || franjaSeleccionada > 3) {
            System.out.println("\n***Franja invalida.***");
            return;
        }

        // A partir de aqui, toda la validacion de reglas de negocio
        // (tipo de pelicula vs. sala, franja ya ocupada) la hace Sala.
        Sala salaSeleccionada = salas[idSala - 1];
        Pelicula peliculaSeleccionada = peliculasRegistradas[idPelicula - 1];
        salaSeleccionada.asignarPelicula(franjaSeleccionada, peliculaSeleccionada);
    }

    // ==================================================================
    // MENU 3: VENTAS
    // (todo el ciclo de venta vive en Sala.venderEntradas)
    // ==================================================================
    private static void menuVentas(Scanner scanner, Sala[] salas) {
        System.out.print("\nIngrese el numero de sala (1, 2 o 3): ");
        int idSala = scanner.nextInt();

        if (idSala < 1 || idSala > 3) {
            System.out.println("\n***Sala invalida.***");
            return;
        }

        System.out.print("\nIngrese la franja horaria (1, 2 o 3): ");
        int franjaSeleccionada = scanner.nextInt();

        if (franjaSeleccionada < 1 || franjaSeleccionada > 3) {
            System.out.println("\n***Franja invalida.***");
            return;
        }

        Sala salaSeleccionada = salas[idSala - 1];
        salaSeleccionada.venderEntradas(scanner, franjaSeleccionada);
    }

}
