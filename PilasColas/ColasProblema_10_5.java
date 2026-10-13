package PilasColas;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ColasProblema_10_5 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in)
        Queue<String> colaNSS = new LinkedList<>();
        Queue<String> colaEntidad = new LinkedList<>();

        LinkedList<Repartidor> listaRepartidores = new LinkedList<>();
        listaRepartidores.add(new Repartidor("111-222", "Tienda A", 3));
        listaRepartidores.add(new Repartidor("333-444", "Tienda B", 5));

        String nss;
        do {

            System.out.println("\n--- Registro Diario de Trabajo ---");
            System.out.print("Ingresa el NSS del repartidor o escribe '0' para terminar: ");
            nss = sc.nextLine();

            if (nss.equals("0")) 
            {
                break;
            }



        } while (true);
    }
}
