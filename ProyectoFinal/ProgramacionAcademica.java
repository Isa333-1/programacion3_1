package ProyectoFinal;

import java.util.Scanner;
import java.util.Stack;

public class ProgramacionAcademica {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Stack<String> historial = new Stack<>();

        int opcion = 0;

        do {
            System.out.println("\n==================================================");
            System.out.println(" PLANIFICACION ACADEMICA - SISTEMA UNIVERSITARIO ");
            System.out.println("==================================================");
            System.out.println("=== GESTION DE ESTUDIANTES ===");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Buscar estudiante por ID");
            System.out.println("3. Listar todos los estudiantes");
            System.out.println("4. Eliminar estudiante");
            System.out.println("\n=== GESTION DE MATERIAS ===");
            System.out.println("5. Crear materia");
            System.out.println("6. Agregar pre-requisito");
            System.out.println("7. Mostrar pre-requisitos");
            System.out.println("8. Inscribir estudiante");
            System.out.println("9. Cancelar inscripcion");
            System.out.println("10. Mostrar cola de espera");
            System.out.println("\n=== GESTION DE HORARIOS ===");
            System.out.println("11. Reservar horario en aula");
            System.out.println("12. Liberar horario");
            System.out.println("13. Consultar disponibilidad");
            System.out.println("\n=== RUTAS ENTRE EDIFICIOS ===");
            System.out.println("14. Agregar conexion entre edificios");
            System.out.println("15. Calcular ruta mas corta");
            System.out.println("\n=== REPORTES ACADEMICOS ===");
            System.out.println("16. Registrar nota");
            System.out.println("17. Ver reporte academico");
            System.out.println("18. Navegador de reportes (atras/adelante)");
            System.out.println("\n=== SISTEMA DESHACER/REHACER ===");
            System.out.println("19. Deshacer ultima operacion");
            System.out.println("20. Rehacer ultima operacion");
            System.out.println("\n=== PROCESAMIENTO POR LOTES ===");
            System.out.println("21. Procesar archivo CSV");
            System.out.println("\n=== SALIR ===");
            System.out.println("22. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.println("\n--- 1. Registrar estudiante ---");
                    break;
                case 2:
                    System.out.println("\n--- 2. Buscar estudiante por ID ---");
                    break;
                case 3:
                    System.out.println("\n--- 3. Listar todos los estudiantes ---");
                    break;
                case 4:
                    System.out.println("\n--- 4. Eliminar estudiante ---");
                    break;
                case 5:
                    System.out.println("\n--- 5. Crear materia ---");
                    break;
                case 6:
                    System.out.println("\n--- 6. Agregar pre-requisito ---");
                    break;
                case 7:
                    System.out.println("\n--- 7. Mostrar pre-requisitos ---");
                    break;
                case 8:
                    System.out.println("\n--- 8. Inscribir estudiante ---");
                    break;
                case 9:
                    System.out.println("\n--- 9. Cancelar inscripcion ---");
                    break;
                case 10:
                    System.out.println("\n--- 10. Mostrar cola de espera ---");
                    break;
                case 11:
                    System.out.println("\n--- 11. Reservar horario en aula ---");
                    break;
                case 12:
                    System.out.println("\n--- 12. Liberar horario ---");
                    break;
                case 13:
                    System.out.println("\n--- 13. Consultar disponibilidad ---");
                    break;
                case 14:
                    System.out.println("\n--- 14. Agregar conexion entre edificios ---");
                    break;
                case 15:
                    System.out.println("\n--- 15. Calcular ruta mas corta ---");
                    break;
                case 16:
                    System.out.println("\n--- 16. Registrar nota ---");
                    break;
                case 17:
                    System.out.println("\n--- 17. Ver reporte academico ---");
                    break;
                case 18:
                    System.out.println("\n--- 18. Navegador de reportes (atras/adelante) ---");
                    break;
                case 19:
                    System.out.println("\n--- 19. Deshacer ultima operacion ---");
                    break;
                case 20:
                    System.out.println("\n--- 20. Rehacer ultima operacion ---");
                    break;
                case 21:
                    System.out.println("\n--- 21. Procesar archivo CSV ---");
                    break;
                case 22:
                    System.out.println("\nSaliendo del sistema...");
                    break;
                default:
                    System.out.println("\nOpcion no valida. Intente de nuevo.");
            }
        } while (opcion != 22);
    
    }

}
