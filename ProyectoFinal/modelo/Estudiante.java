package ProyectoFinal.modelo;
 
import java.util.LinkedList;
import java.util.List;
 
public class Estudiante extends Persona
{
    private int semestre;
    private Double[][] notas = new Double[10][20];
    private List<Materia> historialMaterias = new LinkedList<>();
 
    public Estudiante(String nombre, String id, String email, int semestre)
    {
        super(nombre, id, email);
        this.semestre = semestre;
    }
 
    public int getSemestre()
    {
        return semestre;
    }
 
    public void setSemestre(int semestre)
    {
        this.semestre = semestre;
    }
 
    public Double[][] getNotas()
    {
        return notas;
    }
 
    public List<Materia> getHistorialMaterias()
    {
        return historialMaterias;
    }
 
    public void agregarMateriaHistorial(Materia materia)
    {
        boolean existe = false;
        for (int i = 0; i < historialMaterias.size(); i++)
        {
            if (historialMaterias.get(i).getCodigo().equalsIgnoreCase(materia.getCodigo()))
            {
                existe = true;
                break;
            }
        }
 
        if (!existe)
        {
            historialMaterias.add(materia);
        }
    }
 
    public void removerMateriaHistorial(String codigoMateria)
    {
        for (int i = historialMaterias.size() - 1; i >= 0; i--)
        {
            if (historialMaterias.get(i).getCodigo().equalsIgnoreCase(codigoMateria))
            {
                historialMaterias.remove(i);
            }
        }
    }
 
    public boolean tieneMateriaAprobada(String codigoMateria)
    {
        for (int i = 0; i < historialMaterias.size(); i++)
        {
            if (historialMaterias.get(i).getCodigo().equalsIgnoreCase(codigoMateria))
            {
                return true;
            }
        }
        return false;
    }
 
    public void registrarNota(int numSemestre, int indiceMateria, Double nota)
    {
        int fila = numSemestre - 1;
        if (fila >= 0 && fila < notas.length && indiceMateria >= 0 && indiceMateria < notas[fila].length)
        {
            notas[fila][indiceMateria] = nota;
        }
    }
 
    public double calcularPromedioSemestre(int numSemestre)
    {
        int fila = numSemestre - 1;
        if (fila < 0 || fila >= notas.length)
        {
            return 0.0;
        }
 
        double suma = 0.0;
        int contador = 0;
        for (int j = 0; j < notas[fila].length; j++)
        {
            if (notas[fila][j] != null)
            {
                suma += notas[fila][j];
                contador++;
            }
        }
        return (contador > 0) ? (suma / contador) : 0.0;
    }
 
    public double calcularPromedioAcumulado()
    {
        double suma = 0.0;
        int contador = 0;
        for (int i = 0; i < notas.length; i++)
        {
            for (int j = 0; j < notas[i].length; j++)
            {
                if (notas[i][j] != null)
                {
                    suma += notas[i][j];
                    contador++;
                }
            }
        }
        return (contador > 0) ? (suma / contador) : 0.0;
    }
 
    public int contarAprobadas()
    {
        int aprobadas = 0;
        for (int i = 0; i < notas.length; i++)
        {
            for (int j = 0; j < notas[i].length; j++)
            {
                if (notas[i][j] != null)
                {
                    aprobadas += (notas[i][j] >= 3.0) ? 1 : 0;
                }
            }
        }
        return aprobadas;
    }
 
    public int contarReprobadas()
    {
        int reprobadas = 0;
        for (int i = 0; i < notas.length; i++)
        {
            for (int j = 0; j < notas[i].length; j++)
            {
                if (notas[i][j] != null)
                {
                    reprobadas += (notas[i][j] < 3.0) ? 1 : 0;
                }
            }
        }
        return reprobadas;
    }
 
    @Override
    public void mostrarInformacion()
    {
        double prom = calcularPromedioAcumulado();
        String textoProm = (prom > 0.0) ? String.format("%.2f", prom) : "0.0 (sin notas aun)";
        System.out.println("ID: " + getId()
                        + "\nNombre: " + getNombre()
                        + "\nEmail: " + getEmail()
                        + "\nSemestre: " + semestre
                        + "\nPromedio acumulado: " + textoProm
                    );
       
    }
}
 