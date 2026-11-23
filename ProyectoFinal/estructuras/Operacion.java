package ProyectoFinal.estructuras;
 
import ProyectoFinal.modelo.Estudiante;
 
public class Operacion
{
    private String tipo;
    private String idEstudiante;
    private String codigoMateria;
    private String numeroAula;
    private int dia;
    private int hora;
    private int duracion;
    private int semestre;
    private int indiceNota;
    private Double notaAnterior;
    private Estudiante estudianteRespaldo;
 
    public Operacion(String tipo, String idEstudiante, String codigoMateria)
    {
        this.tipo = tipo;
        this.idEstudiante = idEstudiante;
        this.codigoMateria = codigoMateria;
    }
 
    public static Operacion crearOperacionHorario(String numeroAula, int dia, int hora, int duracion)
    {
        Operacion op = new Operacion("HORARIO", null, null);
        op.numeroAula = numeroAula;
        op.dia = dia;
        op.hora = hora;
        op.duracion = duracion;
        return op;
    }
 
    public static Operacion crearOperacionNota(String idEstudiante, int semestre, int indiceNota, Double notaAnterior)
    {
        Operacion op = new Operacion("NOTA", idEstudiante, null);
        op.semestre = semestre;
        op.indiceNota = indiceNota;
        op.notaAnterior = notaAnterior;
        return op;
    }
 
    public static Operacion crearOperacionEliminarEstudiante(Estudiante estudiante)
    {
        Operacion op = new Operacion("ELIMINAR_ESTUDIANTE", estudiante.getId(), null);
        op.estudianteRespaldo = estudiante;
        return op;
    }
 
    public String getTipo()
    {
        return tipo;
    }
 
    public String getIdEstudiante()
    {
        return idEstudiante;
    }
 
    public String getCodigoMateria()
    {
        return codigoMateria;
    }
 
    public String getNumeroAula()
    {
        return numeroAula;
    }
 
    public int getDia()
    {
        return dia;
    }
 
    public int getHora()
    {
        return hora;
    }
 
    public int getDuracion()
    {
        return duracion;
    }
 
    public int getSemestre()
    {
        return semestre;
    }
 
    public int getIndiceNota()
    {
        return indiceNota;
    }
 
    public Double getNotaAnterior()
    {
        return notaAnterior;
    }
 
    public Estudiante getEstudianteRespaldo()
    {
        return estudianteRespaldo;
    }
}