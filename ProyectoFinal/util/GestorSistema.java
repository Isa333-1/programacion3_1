package ProyectoFinal.util;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import ProyectoFinal.estructuras.ContenedorEstructuras;
import ProyectoFinal.excepciones.ExcepcionesSistema.ArchivoInvalidoException;
import ProyectoFinal.excepciones.ExcepcionesSistema.EstudianteNoEncontradoException;
import ProyectoFinal.modelo.Campus;
import ProyectoFinal.modelo.Estudiante;

public class GestorSistema
{
    private Scanner sc;
    private ContenedorEstructuras estructuras;
    private Campus campus;
 
    public GestorSistema(Scanner sc) throws ArchivoInvalidoException, IOException
    {
        this.sc = sc;
        this.estructuras = new ContenedorEstructuras();
        String[] edificios = {"Ingenieria", "Biblioteca", "Cafeteria", "Rectoria", "Laboratorios"};
        this.campus = new Campus(edificios);
 
        // Carga automatica de estudiantes al iniciar el sistema
        cargarEstudiantesCSV("/workspaces/programacion3_1/ProyectoFinal/estudiantes.csv");
        cargarMateriasCSV("/workspaces/programacion3_1/ProyectoFinal/materias.csv");
    }

    public void registrarEstudiante()
    {
        System.out.println("\nREGISTRO DE ESTUDIANTE");
        System.out.print("ID: ");
        String id = sc.nextLine().trim();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Email: ");
        String email = sc.nextLine().trim();
        System.out.print("Semestre actual: ");
        int semestre = Integer.parseInt(sc.nextLine().trim());
 
        Estudiante nuevo = new Estudiante(nombre, id, email, semestre);
        estructuras.getEstudiantesMap().put(id, nuevo);
        System.out.println("Estudiante registrado exitosamente.");
    }

    public void buscarEstudiantePorId() throws EstudianteNoEncontradoException
    {
        System.out.println("\nBUSCAR ESTUDIANTE");
        System.out.print("ID: ");
        String id = sc.nextLine().trim();
        Estudiante est = estructuras.getEstudiantesMap().get(id);
        if (est == null)
        {
            throw new EstudianteNoEncontradoException("No existe estudiante con ID: " + id);
        }
        System.out.println("Resultado encontrado:");
        est.mostrarInformacion();
    }

      public void listarEstudiantes()
    {
        System.out.println("\nLISTA DE ESTUDIANTES");
        Map<String, Estudiante> mapa = estructuras.getEstudiantesMap();
        if (mapa.isEmpty())
        {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
 
        for (Map.Entry<String, Estudiante> entry : mapa.entrySet())
        {
            entry.getValue().mostrarInformacion();
            System.out.println("------------------------------------------");
        }
    }


}