package PilasColas;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ColasProblem_10_3 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        Queue carritoCompra = new LinkedList<>();
        Queue caja1 = new LinkedList<>();
        Queue caja2 = new LinkedList<>();
        Queue caja3 = new LinkedList<>();

        for (int i = 1; i <= 25; i++) 
        {
            carritoCompra.add(i);
        }

        int opcion;

        do {

            System.out.println("------------- Supermercado -----------");
            System.out.println("1. Llegada de un cliente"
                                + "2. Cliente pago en caja"
                                + "3. Ver cantidad de carritos"
                                + "4. Ver estado de cajas"
                                + "5. Salir"
            );
            System.out.println("- Elije una opcion");
            opcion = sc.nextInt();
            
        } while (opcion != 5);


    }
    

    
}
