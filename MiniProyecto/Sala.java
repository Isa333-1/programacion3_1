package MiniProyecto;

import java.util.Scanner;

public class Sala 
{
       // ---- Atributos privados ----
    private int salaId;
    private String[][] mapaAsientos;
    private boolean soloPermite3D;       // true unicamente en la Sala 3
    private Funcion[] funcionesDelDia;   // arreglo de tamaño fijo: 3 franjas horarias

    /**
     * Constructor de Sala. Segun el id de sala se inicializa manualmente
     * la matriz de sillas correspondiente.
     */
    public Sala(int pSalaId) {
        salaId = pSalaId;

        // Se crean las 3 funciones (franjas horarias) de la sala, vacias al inicio
        funcionesDelDia = new Funcion[3];
        funcionesDelDia[0] = new Funcion(1);
        funcionesDelDia[1] = new Funcion(2);
        funcionesDelDia[2] = new Funcion(3);

        if (salaId == 1 || salaId == 2) {
            // Salas 1 y 2: 6 filas normales (A-F) + 2 filas preferenciales (G,H)
            // Las filas G y H solo tienen sillas fisicas entre las columnas 3 y 9.
            soloPermite3D = false;
            mapaAsientos = new String[][]{
                    {" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"},
                    {"H", " ", " ", "-", "-", "-", "-", "-", "-", "-", "", "", "_"},
                    {"G", " ", " ", "-", "-", "-", "-", "-", "-", "-", "", "", "_"},
                    {"F", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
                    {"E", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
                    {"D", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
                    {"C", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
                    {"B", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
                    {"A", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"}
            };
        } else {
            // Sala 3: solo 6 filas (A-F), sin seccion preferencial. Solo peliculas 3D.
            soloPermite3D = true;
            mapaAsientos = new String[][]{
                    {" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"},
                    {"F", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
                    {"E", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
                    {"D", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
                    {"C", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
                    {"B", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
                    {"A", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"}
            };
        }
    }

    // ---- Metodos "getters" ----
    public int getSalaId() {
        return salaId;
    }

    public boolean isSoloPermite3D() {
        return soloPermite3D;
    }

    public Funcion[] getFuncionesDelDia() {
        return funcionesDelDia;
    }

    /**
     * Busca el indice de fila dentro de la matriz que corresponde a la letra
     * indicada (ej: "A", "G"). Devuelve -1 si esa fila no existe en esta sala.
     */
    public int buscarIndiceFila(String pFila) {
        // indiceEncontrado inicia en -1 (valor "no encontrado") y solo cambia
        // si alguna fila de la matriz coincide con la letra buscada.
        int indiceEncontrado = -1;
        for (int indiceFila = 1; indiceFila < mapaAsientos.length; indiceFila = indiceFila + 1) {
            if (mapaAsientos[indiceFila][0].equals(pFila)) {
                indiceEncontrado = indiceFila;
            }
        }
        return indiceEncontrado;
    }

    /**
     * Verifica si el numero de silla (columna) esta dentro del rango valido (1 a 12).
     */
    public boolean columnaValida(int pColumna) {
        return pColumna >= 1 && pColumna <= mapaAsientos[0].length - 1;
    }

    /**
     * Determina si la fila indicada corresponde a la seccion preferencial.
     * Solo aplica para las Salas 1 y 2, filas G y H.
     */
    public boolean esPreferencial(String pFila) {
        boolean esFilaPreferencial = false;
        if (salaId == 1 || salaId == 2) {
            if (pFila.equals("G") || pFila.equals("H")) {
                esFilaPreferencial = true;
            }
        }
        return esFilaPreferencial;
    }

    /**
     * Muestra en consola el mapa completo de sillas de la sala.
     */
    public void mostrarMapa() {
        System.out.println("--- Mapa de sillas - Sala " + salaId + " ---");
        for (int indiceFila = 0; indiceFila < mapaAsientos.length; indiceFila = indiceFila + 1) {
            for (int indiceColumna = 0; indiceColumna < mapaAsientos[indiceFila].length; indiceColumna = indiceColumna + 1) {
                System.out.print(mapaAsientos[indiceFila][indiceColumna] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Intenta vender la silla indicada por fila y columna.
     * Devuelve el precio de la silla si la venta fue exitosa, o -1 si no se pudo
     * realizar (fila/columna invalida, sin silla fisica, o ya vendida).
     * Toda la validacion se hace con if / else, sin try/catch.
     */
    public double venderSilla(String pFila, int pColumna) {
        // precioResultante inicia en -1 como "bandera de error": si ninguna
        // validacion falla, se sobrescribe con el precio real de la silla.
        double precioResultante = -1;
        int indiceFila = buscarIndiceFila(pFila);

        if (indiceFila == -1) {
            System.out.println("La fila \"" + pFila + "\" no existe en esta sala.");
        } else if (!columnaValida(pColumna)) {
            System.out.println("La silla numero " + pColumna + " no existe en esta sala.");
        } else if (mapaAsientos[indiceFila][pColumna].equals("_")) {
            System.out.println("En esa posicion no hay silla fisica.");
        } else if (mapaAsientos[indiceFila][pColumna].equals(" ")) {
            System.out.println("Esa posicion no corresponde a una silla valida.");
        } else if (mapaAsientos[indiceFila][pColumna].equals("X")) {
            System.out.println("La silla " + pFila + pColumna + " ya fue vendida.");
        } else {
            // La silla esta disponible ("-"): se marca como vendida
            mapaAsientos[indiceFila][pColumna] = "X";
            if (soloPermite3D) {
                precioResultante = 10000;
            } else if (esPreferencial(pFila)) {
                precioResultante = 12000;
            } else {
                precioResultante = 8000;
            }
        }
        return precioResultante;
    }

    /**
     * Cuenta cuantas sillas siguen disponibles ("-") en la sala.
     */
    public int contarDisponibles() {
        int totalDisponibles = 0;
        for (int indiceFila = 1; indiceFila < mapaAsientos.length; indiceFila = indiceFila + 1) {
            for (int indiceColumna = 1; indiceColumna < mapaAsientos[indiceFila].length; indiceColumna = indiceColumna + 1) {
                if (mapaAsientos[indiceFila][indiceColumna].equals("-")) {
                    totalDisponibles = totalDisponibles + 1;
                }
            }
        }
        return totalDisponibles;
    }

    /**
     * Muestra en consola las funciones (franjas horarias) de la sala,
     * delegando el detalle de cada una en Funcion.mostrarInfo().
     */
    public void mostrarFunciones() {
        System.out.println("--- Funciones Sala " + salaId + " ---");
        for (int indiceFuncion = 0; indiceFuncion < funcionesDelDia.length; indiceFuncion = indiceFuncion + 1) {
            funcionesDelDia[indiceFuncion].mostrarInfo();
        }
    }

    /**
     * Valida el tipo de la pelicula contra las reglas de la sala (3D solo en
     * Sala 3, 35mm/normal solo en Salas 1 y 2) y, si es compatible, delega
     * la asignacion en la Funcion correspondiente.
     */
    public boolean asignarPelicula(int pFranjaHoraria, Pelicula pPelicula) {
        boolean tipoCompatible;

        if (soloPermite3D) {
            tipoCompatible = pPelicula.getTipoProyeccion().equals("3D");
            if (!tipoCompatible) {
                System.out.println("La Sala " + salaId + " solo admite peliculas 3D.");
            }
        } else {
            tipoCompatible = !pPelicula.getTipoProyeccion().equals("3D");
            if (!tipoCompatible) {
                System.out.println("La Sala " + salaId + " no admite peliculas 3D.");
            }
        }

        boolean asignacionExitosa = false;
        if (tipoCompatible) {
            Funcion funcionSeleccionada = funcionesDelDia[pFranjaHoraria - 1];
            asignacionExitosa = funcionSeleccionada.intentarAsignar(pPelicula);
        }
        return asignacionExitosa;
    }

    /**
     * Ejecuta el modulo de ventas completo para una funcion de esta sala:
     * valida que tenga pelicula asignada, muestra el mapa, vende sillas en
     * un ciclo hasta que el usuario decida parar, y muestra el resumen final.
     */
    public void venderEntradas(Scanner scanner, int pFranjaHoraria) {
        Funcion funcionSeleccionada = funcionesDelDia[pFranjaHoraria - 1];

        if (!funcionSeleccionada.tieneAsignacion()) {
            System.out.println("Esta funcion todavia no tiene una pelicula asignada.");
            return;
        }

        System.out.println("Funcion: " + funcionSeleccionada.getPeliculaAsignada().getNombre()
                + " (" + funcionSeleccionada.getHorarioTexto() + ")");

        double totalAcumulado = 0;
        // deseaContinuar controla el ciclo de ventas. Se usa do-while porque
        // se debe ofrecer comprar al menos una silla, y luego el modulo sigue
        // activo mientras el usuario responda "S" (si).
        String deseaContinuar;

        do {
            mostrarMapa();
            System.out.println("Sillas disponibles en esta funcion: " + contarDisponibles());

            System.out.print("Ingrese la fila de la silla (ej: A): ");
            // Se lee como String y se convierte a mayuscula (no se usa char ni equalsIgnoreCase)
            String filaIngresada = scanner.next().toUpperCase();

            System.out.print("Ingrese el numero de la silla: ");
            int columnaIngresada = scanner.nextInt();

            double precioSilla = venderSilla(filaIngresada, columnaIngresada);

            if (precioSilla == -1) {
                System.out.println("No fue posible vender esa silla, intente con otra.");
            } else {
                totalAcumulado = totalAcumulado + precioSilla;
                System.out.println("Silla " + filaIngresada + columnaIngresada + " vendida por $" + precioSilla
                        + ". Total acumulado: $" + totalAcumulado);
            }

            System.out.print("Desea comprar otra silla? (S = Si / N = No): ");
            deseaContinuar = scanner.next().toUpperCase();
        } while (deseaContinuar.equals("S"));

        System.out.println("\n--- Resumen de la Venta ---");
        System.out.println("Total a pagar: $" + totalAcumulado);
        System.out.println("Sillas disponibles restantes en la funcion: " + contarDisponibles());
    }
   






}
