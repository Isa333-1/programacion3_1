package MiniProyecto;

import java.util.Scanner;

public class Pelicula 
{
     // ---- Atributos privados ----
    private String nombre;
    private String idioma;
    private String tipoProyeccion;   // "35mm" o "3D"
    private int duracionMinutos;

    /**
     * Constructor de Pelicula.
     * Nota: los parametros usan el prefijo "p" para diferenciarlos de los
     * atributos de la clase,**/
    public Pelicula(String pNombre, String pIdioma, String pTipoProyeccion, int pDuracionMinutos) 
    {
        nombre = pNombre;
        idioma = pIdioma;
        tipoProyeccion = pTipoProyeccion;
        duracionMinutos = pDuracionMinutos;
    }

    // ---- Metodos "getters" ----
    public String getNombre() 
    {
        return nombre;
    }

    public String getIdioma() 
    {
        return idioma;
    }

    public String getTipoProyeccion() 
    {
        return tipoProyeccion;
    }

    public int getDuracionMinutos() 
    {
        return duracionMinutos;
    }

    /**
     * Muestra en consola la informacion resumida de la pelicula.
     */
    public void mostrarInfo() {
        System.out.println("Nombre: " + nombre
                + "\nIdioma: " + idioma
                + "\nTipo: " + tipoProyeccion
                + "\nDuracion: " + duracionMinutos + " min");
    }

    /**
     * Le pregunta al usuario, por consola, los datos de una nueva pelicula
     * y construye el objeto. Toda la captura de datos vive aqui (y no en Main)
     * para que cada clase se encargue de su propia responsabilidad.
     */
    public static Pelicula solicitarDatos(Scanner scanner) {
        scanner.nextLine(); // se limpia el salto de linea pendiente del ultimo nextInt()

        System.out.print("Nombre de la pelicula: ");
        String nombreIngresado = scanner.nextLine();

        System.out.print("Idioma: ");
        String idiomaIngresado = scanner.nextLine();

        // Se usa do-while porque la pregunta debe hacerse SI O SI al menos una
        // vez; luego se repite solo si el usuario digita una opcion invalida.
        int opcionTipo;
        do {
            System.out.println("Tipo de pelicula:");
            System.out.println("1. 35mm");
            System.out.println("2. 3D");
            System.out.print("Seleccione: ");
            opcionTipo = scanner.nextInt();

            switch (opcionTipo) {
                case 1:
                    break;
                case 2:
                    break;
                default:
                    System.out.println("\n***Opcion invalida, intente de nuevo.***");
            }
        } while (opcionTipo != 1 && opcionTipo != 2);
        // Operador ternario para traducir la opcion numerica al texto del tipo
        String tipoIngresado = opcionTipo == 1 ? "35mm" : "3D";

        System.out.print("Duracion en minutos: ");
        int duracionIngresada = scanner.nextInt();

        return new Pelicula(nombreIngresado, idiomaIngresado, tipoIngresado, duracionIngresada);
    }

}
