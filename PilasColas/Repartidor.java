package PilasColas;

public class Repartidor 
{
    private String nss;
    private String entidad;
    private int diasTrabajados;

    public Repartidor(String nss, String entidad, int diasTrabajados) 
    {
        this.nss = nss;
        this.entidad = entidad;
        this.diasTrabajados = diasTrabajados;
    }

    public void setEntidad(String entidad) 
    {
        this.entidad = entidad;
    }

    public String getNss() 
    {
        return nss;
    }

    public String getEntidad() 
    {
        return entidad;
    }

    public int getDiasTrabajados() 
    {
        return diasTrabajados;
    }

    public void incrementarDias() 
    {
        diasTrabajados++;
    }

    @Override
    public String toString() 
    {
        return "[NSS: " + nss + ", Entidad: " + entidad + ", Días Trabajados: " + diasTrabajados + "]";
    }


}
