package ProyectoFinal.modelo;
 
import ProyectoFinal.excepciones.ExcepcionesSistema.HorarioConflictivoException;
 
public class Aula
{
    private String numeroAula;
    private int capacidad;
    private boolean[][] disponibilidad = new boolean[7][24];
 
    public Aula(String numeroAula, int capacidad)
    {
        this.numeroAula = numeroAula;
        this.capacidad = capacidad;
    }
 
    public String getNumeroAula()
    {
        return numeroAula;
    }
 
    public int getCapacidad()
    {
        return capacidad;
    }
 
    public boolean[][] getDisponibilidad()
    {
        return disponibilidad;
    }
 
    public boolean consultarDisponibilidad(int dia, int hora)
    {
        if (dia < 0 || dia > 6 || hora < 0 || hora > 23)
        {
            return false;
        }
        return !disponibilidad[dia][hora];
    }
 
    public void reservar(int dia, int hora, int duracion) throws HorarioConflictivoException
    {
        if (dia < 0 || dia > 6 || hora < 0 || duracion <= 0 || (hora + duracion) > 24)
        {
            throw new HorarioConflictivoException("Rango de dia, hora o duracion invalido.");
        }
        for (int h = hora; h < hora + duracion; h++)
        {
            if (disponibilidad[dia][h])
            {
                throw new HorarioConflictivoException("Dia " + dia + " " + h + ":00 ya esta reservado.");
            }
        }
        for (int h = hora; h < hora + duracion; h++)
        {
            disponibilidad[dia][h] = true;
        }
    }
 
    public void liberar(int dia, int hora, int duracion)
    {
        if (dia < 0 || dia > 6 || hora < 0 || duracion <= 0 || (hora + duracion) > 24)
        {
            return;
        }
        for (int h = hora; h < hora + duracion; h++)
        {
            disponibilidad[dia][h] = false;
        }
    }
}
