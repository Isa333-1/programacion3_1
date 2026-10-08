import java.util.Stack;

public class PilasEjemplo 
{
    public static void main(String[] args) 
    {
        //Ejemplo de un historial de busqueda web
        Stack<String> historial = new Stack<>();

        historial.push("youtube.com");
        historial.push("github.com");
        historial.push("teams.com");
        historial.push("whatsapp.com");

        System.out.println("¿Esta vacío el historial? " + historial.empty());
    }
}
