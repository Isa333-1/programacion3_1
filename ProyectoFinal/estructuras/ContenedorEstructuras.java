package ProyectoFinal.estructuras;
 
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;
import ProyectoFinal.modelo.Aula;
import ProyectoFinal.modelo.Estudiante;
import ProyectoFinal.modelo.Facultad;
import ProyectoFinal.modelo.Materia;
 
public class ContenedorEstructuras
{
    private Map<String, Estudiante> estudiantesMap = new HashMap<>();
    private Map<String, Aula> aulasMap = new TreeMap<>();
    private Map<String, Materia> materiasMap = new HashMap<>();
    private Stack<Operacion> pilaDeshacer = new Stack<>();
    private Stack<Operacion> pilaRehacer = new Stack<>();
    private Stack<String> pilaReportes = new Stack<>();
    private Stack<String> pilaAdelanteReportes = new Stack<>();
    private Queue<String[]> colaBatch = new ArrayDeque<>();
    private Facultad[] facultades = new Facultad[5];
 
    public ContenedorEstructuras()
    {
        facultades[0] = new Facultad("FING", "Ingenieria");
        facultades[1] = new Facultad("FCEN", "Ciencias Exactas");
        facultades[2] = new Facultad("FADM", "Administracion");
        facultades[3] = new Facultad("FHUM", "Humanidades");
        facultades[4] = new Facultad("FSAL", "Salud");
 
        aulasMap.put("101", new Aula("101", 35));
        aulasMap.put("202", new Aula("202", 40));
    }
 
    public Map<String, Estudiante> getEstudiantesMap()
    {
        return estudiantesMap;
    }
 
    public Map<String, Aula> getAulasMap()
    {
        return aulasMap;
    }
 
    public Map<String, Materia> getMateriasMap()
    {
        return materiasMap;
    }
 
    public Stack<Operacion> getPilaDeshacer()
    {
        return pilaDeshacer;
    }
 
    public Stack<Operacion> getPilaRehacer()
    {
        return pilaRehacer;
    }
 
    public Stack<String> getPilaReportes()
    {
        return pilaReportes;
    }
 
    public Stack<String> getPilaAdelanteReportes()
    {
        return pilaAdelanteReportes;
    }
 
    public Queue<String[]> getColaBatch()
    {
        return colaBatch;
    }
 
    public Facultad[] getFacultades()
    {
        return facultades;
    }
}