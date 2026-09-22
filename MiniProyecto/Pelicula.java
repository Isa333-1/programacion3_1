package MiniProyecto;

public class Pelicula 
{
    private String nombre;
    private String idioma;
    private String tipo;
    private int duracion;

    public Pelicula()
    {
        nombre = "";
        idioma = "";
        tipo = "";
        duracion = 0;
    }

    public Pelicula(String nom, String idio, String tip, int dur)
    {
        nombre = nom;
        idioma = idio;
        tipo = tip;
        duracion = dur;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getIdioma()
    {
        return nombre;
    }

    public String getTipo()
    {
        return tipo;
    }

    public int getDuracion()
    {
        return duracion;
    }

    public void imprimir()
    {
        System.out.println("Titulo: " + nombre + "\nIdioma: " + idioma + "\nTipo: " + tipo + "\nDuracion: " + duracion + " minutos");
    }



}
