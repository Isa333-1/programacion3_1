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

        System.out.println("¿Esta vacío el historial? " + "\nR// " + historial.empty());
        System.out.println("¿Cual fue la ultima pagina en la que se navego? "+ "\nR// " + historial.peek());
        System.out.println("¿En que posicion del historial esta Youtube?" + "\nR// " + historial.search("youtube.com"));
        System.out.println();
    }
}
