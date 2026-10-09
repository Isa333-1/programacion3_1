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

        int opcion = 0;

        do {

            System.out.println("\n------------ Historial -----------");
            System.out.println("1. Estado del historial" 
                                + "\n2. Ultima pagina de navegacion" 
                                + "\n3. Posicion de la busqueda youtube"
                                + "\n4. Volver a la pagina anterior"
                                + "\n5. Pagina actual"
                                + "\n6. Total de sitios web del historial"
                                + "\n7. Capacidad del historial"
                                + "\n8. Buscar sitios web en el historial"
                                + "\n9. Posicion de sitios web en el historial"
                                + "\n10. p"
            );

            opcion = sc.nextInt();

            switch (opcion) 
            {
                case 1:
                    System.out.println("\n¿Esta vacío el historial? " + "\nR// " + historial.empty());
                    break;
                case 2:
                    System.out.println("\nLa ultima pagina de navegacion fue: " + historial.peek());
                    break;
                case 3:
                    System.out.println("\nYoutube esta en la posicion " + historial.search("youtube.com") + " del historial");
                    break;
                case 4:
                    System.out.println("\nLa pagina actual es: " + historial.pop() + " \nVolviendo a la pagina anterior..." + "\nLa pagina actual ahora es: " + historial.peek());
                    break;
                case 5:
                    System.out.println("\nPagina actual: " + historial.peek());
                    break;
                case 6:
                    System.out.println("\nEn el historial hay " + historial.size() + " sitios web");
                    break;
                case 7:
                    System.out.println("\nCapacidad del historial: " + historial.capacity());
                    break;
                case 8:
                    System.out.println("\n¿En el historial esta spotify?" + "\nR// " + historial.contains("spotify.com"));
                    System.out.println("¿En el historial esta youtube? " + "\nR// " + historial.contains("youtube.com"));
                    break;
                case 9:
                    System.out.println("\n¿Cuales el primer y tercer sitio visitado? " + "\nR// La primera busqueda fue: " 
                            + historial.get(0) + "\n   La tercera busqueda fue: " + historial.elementAt(2));
                    System.out.println("¿Cual fue el ultimo sitio visitado? " + "\nR// " + historial.lastElement());
                    break;
                case 10:
                    System.out.println("\np" + historial.indexOf("github.com"));
                    break;
                case 11:

                    break;

                default:
                    break;
            }

            
        } while (opcion != 9);

    }
}
