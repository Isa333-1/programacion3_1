package ProyectoFinal.modelo;
 
import ProyectoFinal.excepciones.ExcepcionesSistema.ColaDeEsperaVaciaException;
import ProyectoFinal.excepciones.ExcepcionesSistema.CupoLlenoException;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
 
public class Materia {
    private String codigo;
    private String nombre;
    private int cuposMaximos;
    private int creditos;
    private List<Materia> preRequisitos = new LinkedList<>();
    private List<Estudiante> inscritos = new LinkedList<>();
    private Queue<Estudiante> colaEspera = new ArrayDeque<>();;
 
    public Materia(String codigo, String nombre, int cuposMaximos, int creditos)
    {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cuposMaximos = cuposMaximos;
        this.creditos = creditos;
    }
 
    public String getCodigo()
    {
        return codigo;
    }
 
    public String getNombre()
    {
        return nombre;
    }
 
    public int getCuposMaximos()
    {
        return cuposMaximos;
    }
 
    public int getCreditos()
    {
        return creditos;
    }
 
    public List<Materia> getPreRequisitos()
    {
        return preRequisitos;
    }
 
    public List<Estudiante> getInscritos()
    {
        return inscritos;
    }
 
    public Queue<Estudiante> getColaEspera()
    {
        return colaEspera;
    }
 
    public void agregarPreRequisito(Materia materia)
    {
        boolean yaEsta = false;
        for (int i = 0; i < preRequisitos.size(); i++)
        {
            if (preRequisitos.get(i).getCodigo().equalsIgnoreCase(materia.getCodigo()))
            {
                yaEsta = true;
                break;
            }
        }
        if (!yaEsta)
        {
            preRequisitos.add(materia);
        }
    }
 
    public int getCuposRestantes()
    {
        int restantes = cuposMaximos - inscritos.size();
        return (restantes >= 0) ? restantes : 0;
    }
 
    public boolean estaLlena()
    {
        return inscritos.size() >= cuposMaximos;
    }
 
    public void inscribirDirecto(Estudiante estudiante) throws CupoLlenoException
    {
        if (estaLlena())
        {
            throw new CupoLlenoException("La materia " + codigo + " no tiene cupos disponibles.");
        }
        boolean yaInscrito = false;
        for (int i = 0; i < inscritos.size(); i++)
        {
            if (inscritos.get(i).getId().equals(estudiante.getId()))
            {
                yaInscrito = true;
                break;
            }
        }
 
        if (!yaInscrito)
        {
            inscritos.add(estudiante);
            estudiante.agregarMateriaHistorial(this);
        }
    }
 
    public void encolarEstudiante(Estudiante estudiante)
    {
        boolean enCola = false;
        for (Estudiante e : colaEspera)
        {
            if (e.getId().equals(estudiante.getId()))
            {
                enCola = true;
                break;
            }
        }
       
        if (!enCola)
        {
            colaEspera.offer(estudiante);
        }
    }
 
    public Estudiante desencolarSiguiente() throws ColaDeEsperaVaciaException
    {
        if (colaEspera.isEmpty())
        {
            throw new ColaDeEsperaVaciaException("La cola de espera para " + codigo + " esta vacia.");
        }
        return colaEspera.poll();
    }
 
    public void cancelarInscripcion(Estudiante estudiante)
    {
        for (int i = inscritos.size() - 1; i >= 0; i--)
        {
            if (inscritos.get(i).getId().equals(estudiante.getId()))
            {
                inscritos.remove(i);
            }
        }
        estudiante.removerMateriaHistorial(this.codigo);
    }
}