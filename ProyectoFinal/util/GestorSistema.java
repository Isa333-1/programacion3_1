package ProyectoFinal.util;

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



}