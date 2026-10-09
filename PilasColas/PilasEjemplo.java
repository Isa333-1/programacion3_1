package PilasColas;
import java.util.Scanner;
import java.util.Stack;

public class PilasEjemplo 
{
    public static void main(String[] args) 
    {
        //Ejemplo de un historial de busqueda web
        Scanner sc = new Scanner(System.in);
        Stack<String> historial = new Stack<>();

        historial.push("youtube.com");
        historial.push("github.com");
        historial.push("teams.com");
        historial.push("whatsapp.com");

        System.out.println("¿Esta vacío el historial? " + "\nR// " + historial.empty());
        System.out.println("¿Cual fue la ultima pagina en la que se navego? "+ "\nR// " + historial.peek());
        System.out.println("¿En que posicion del historial esta Youtube?" +  + historial.search("youtube.com"));
        /*System.out.println("Desea volver a la pagina anterior?"
                            + "\n1. Si"
                            + "\n2. No"
                            + "\nIngrese la opcion: "
        );

        int opcion = sc.nextInt();     
        
        if(opcion == 1)
            System.out.println("La pagina actual es: " + historial.pop() + " \nVolviendo a la pagina anterior..." + "\nLa pagina actual ahora es: " + historial.peek());
        else if(opcion == 2)
            System.out.println("Pagina actual: " + historial.peek() + " no se hizo ningun cambio");
        else
            System.out.println("La opcion no es valida vuelva a intentar");*/

        System.out.println("¿Cuantos sitios web hay en el historial? " + "\nR// " + historial.size());
        System.out.println("Capacidad del historial: " + "\nR// " + historial.capacity());
        System.out.println("¿En el historial esta spotify?" + "\nR// " + historial.);


    }
}
