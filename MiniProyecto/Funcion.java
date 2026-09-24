package MiniProyecto;

public class Funcion 
{
    // ---- Atributos privados ----
    private int franjaHoraria;         // 1, 2 o 3
    private Pelicula peliculaAsignada; // null si aun no se asigna
    private boolean tieneAsignacion;   // indica si ya tiene una pelicula asignada

    /**
     * Constructor de Funcion. Recibe el numero de franja (1, 2 o 3).
     */
    public Funcion(int pFranjaHoraria) {
        franjaHoraria = pFranjaHoraria;
        peliculaAsignada = null;
        tieneAsignacion = false;
    }

    // ---- Metodos "getters" ----
    public int getFranjaHoraria() {
        return franjaHoraria;
    }

    public Pelicula getPeliculaAsignada() {
        return peliculaAsignada;
    }

    public boolean tieneAsignacion() {
        return tieneAsignacion;
    }

    /**
     * Devuelve el horario en texto correspondiente a la franja,
     * usando unicamente estructuras condicionales (sin char).
     */
    public String getHorarioTexto() {
        String horario;
        if (franjaHoraria == 1) {
            horario = "14:00 - 16:30";
        } else if (franjaHoraria == 2) {
            horario = "16:30 - 19:00";
        } else {
            horario = "19:00 - 21:00";
        }
        return horario;
    }

    /**
     * Intenta asignar una pelicula a esta funcion. Si la franja ya tenia
     * una pelicula asignada, no la reemplaza y devuelve false; de lo
     * contrario, hace la asignacion y devuelve true.
     */
    public boolean intentarAsignar(Pelicula pPelicula) {
        boolean asignacionExitosa;
        if (tieneAsignacion) {
            System.out.println("La franja " + franjaHoraria + " (" + getHorarioTexto()
                    + ") ya tiene una pelicula asignada.");
            asignacionExitosa = false;
        } else {
            peliculaAsignada = pPelicula;
            tieneAsignacion = true;
            System.out.println("Pelicula asignada con exito a la franja " + franjaHoraria
                    + " (" + getHorarioTexto() + ").");
            asignacionExitosa = true;
        }
        return asignacionExitosa;
    }

    /**
     * Muestra en consola el estado de la funcion (con o sin pelicula asignada).
     */
    public void mostrarInfo() {
        if (tieneAsignacion) {
            System.out.println("Franja " + franjaHoraria + " (" + getHorarioTexto()
                    + "): " + peliculaAsignada.getNombre());
        } else {
            System.out.println("Franja " + franjaHoraria + " (" + getHorarioTexto() + "): Sin asignar");
        }
    }
}
