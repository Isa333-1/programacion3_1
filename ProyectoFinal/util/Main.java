package ProyectoFinal.util;
 
import ProyectoFinal.excepciones.ExcepcionesSistema.ArchivoInvalidoException;
import ProyectoFinal.excepciones.ExcepcionesSistema.ColaDeEsperaVaciaException;
import ProyectoFinal.excepciones.ExcepcionesSistema.CupoLlenoException;
import ProyectoFinal.excepciones.ExcepcionesSistema.EstudianteNoEncontradoException;
import ProyectoFinal.excepciones.ExcepcionesSistema.HorarioConflictivoException;
import ProyectoFinal.excepciones.ExcepcionesSistema.PilaDeshacerVaciaException;
import ProyectoFinal.excepciones.ExcepcionesSistema.PreRequisitoNoAprobadoException;
import java.io.IOException;
import java.util.Scanner;
 
public class Main {
 
    public static void main(String[] args)
            throws EstudianteNoEncontradoException, PreRequisitoNoAprobadoException,
            CupoLlenoException, ColaDeEsperaVaciaException, HorarioConflictivoException,
            PilaDeshacerVaciaException, ArchivoInvalidoException, IOException    
        {
 
        Scanner sc = new Scanner(System.in);
        GestorSistema gestor = new GestorSistema(sc);
        int opcion = 0;
 
        do {
            System.out.println("\nPLANIFICACIÓN ACADÉMICA");
            System.out.println("SISTEMA UNIVERSITARIO");
            System.out.println("=== GESTION DE ESTUDIANTES ===");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Buscar estudiante por ID");
            System.out.println("3. Listar todos los estudiantes");
            System.out.println("4. Eliminar estudiante");
            System.out.println("=== GESTION DE MATERIAS ===");
            System.out.println("5. Crear materia");
            System.out.println("6. Agregar pre-requisito");
            System.out.println("7. Mostrar pre-requisitos");
            System.out.println("8. Inscribir estudiante");
            System.out.println("9. Cancelar inscripcion");
            System.out.println("10. Mostrar cola de espera");
            System.out.println("=== GESTION DE HORARIOS ===");
            System.out.println("11. Reservar horario en aula");
            System.out.println("12. Liberar horario");
            System.out.println("13. Consultar disponibilidad");
            System.out.println("=== RUTAS ENTRE EDIFICIOS ===");
            System.out.println("14. Agregar conexion entre edificios");
            System.out.println("15. Calcular ruta mas corta");
            System.out.println("=== REPORTES ACADEMICOS ===");
            System.out.println("16. Registrar nota");
            System.out.println("17. Ver reporte academico");
            System.out.println("18. Navegador de reportes (atras/adelante)");
            System.out.println("=== SISTEMA DESHACER/REHACER ===");
            System.out.println("19. Deshacer última operacion");
            System.out.println("20. Rehacer ultima operacion");
            System.out.println("=== PROCESAMIENTO POR LOTES ===");
            System.out.println("21. Procesar archivo CSV");
            System.out.println("=== SALIR ===");
            System.out.println("22. Salir");
            System.out.print("Seleccione una opcion: ");
 
            opcion = Integer.parseInt(sc.nextLine().trim());
 
            switch (opcion) {
                case 1:
                    gestor.registrarEstudiante();
                    break;
                case 2:
                    gestor.buscarEstudiantePorId();
                    break;
                case 3:
                    gestor.listarEstudiantes();
                    break;
                case 4:
                    gestor.eliminarEstudiante();
                    break;
                case 5:
                    gestor.crearMateria();
                    break;
                case 6:
                    gestor.agregarPreRequisito();
                    break;
                case 7:
                    gestor.mostrarPreRequisitos();
                    break;
                case 8:
                    gestor.inscribirEstudiante();
                    break;
                case 9:
                    gestor.cancelarInscripcion();
                    break;
                case 10:
                    gestor.mostrarColaEspera();
                    break;
                case 11:
                    gestor.reservarHorario();
                    break;
                case 12:
                    gestor.liberarHorario();
                    break;
                case 13:
                    gestor.consultarDisponibilidad();
                    break;
                case 14:
                    gestor.agregarConexionEdificios();
                    break;
                case 15:
                    gestor.calcularRutaMasCorta();
                    break;
                case 16:
                    gestor.registrarNota();
                    break;
                case 17:
                    gestor.verReporteAcademico();
                    break;
                case 18:
                    gestor.navegadorReportes();
                    break;
                case 19:
                    gestor.deshacerOperacion();
                    break;
                case 20:
                    gestor.rehacerOperacion();
                    break;
                case 21:
                    gestor.procesarArchivoCSV();
                    break;
                case 22:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        } while (opcion != 22);
 
        sc.close();
    }
}