package PilasColas;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ColasProblem_10_3 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        Queue<Integer> carritoCompra = new LinkedList<>();
        Queue<Integer> caja1 = new LinkedList<>();
        Queue<Integer> caja2 = new LinkedList<>();
        Queue<Integer> caja3 = new LinkedList<>();

        for (int i = 1; i <= 25; i++) 
        {
            carritoCompra.add(i);
        }

        int cliente = 1;
        int opcion;

        do {

            System.out.println("\n------------- Supermercado -----------");
            System.out.println("\n1. Llegada de un cliente"
                                + "\n2. Cliente pago en caja"
                                + "\n3. Ver cantidad de carritos"
                                + "\n4. Ver estado de cajas"
                                + "\n5. Salir"
            );
            System.out.print("\n- Elije una opcion: ");
            opcion = sc.nextInt();

            switch (opcion) 
            {
                case 1:
                    if (carritoCompra.isEmpty()) 
                    {
                        System.out.println("NO hay carritos disponibles para el cliente "  + cliente + " porfavor espere");
                    }else{
                       
                        String clienteAtendido = "El cliente numero " + cliente + " tiene el carrito numero " + carritoCompra.poll();
                    }

                    if (caja1.size() <= caja2.size() && caja1.size() <= caja3.size()) 
                    {
                        caja1.add(cliente);
                        System.out.println("El cliente numero " + cliente + " se ha ubicado en la CAJA 1.");
                    } else if (caja2.size() <= caja1.size() && caja2.size() <= caja3.size()) {
                        caja2.add(cliente);
                        System.out.println("El cliente numero " + cliente + " se ha ubicado en la CAJA 2.");
                    } else {
                        caja3.add(cliente);
                        System.out.println("El cliente numero " + cliente + " se ha ubicado en la CAJA 3.");
                    }

                    
                    break;
                case 2:
                    
                    break;
                case 3:
                    
                    break;
                case 4:
                    
                    break;
                case 5:
                    
                    break;
            
                default:
                    System.out.println("Opcion invalida intente de nuevo");
                    break;
            }
            
        } while (opcion != 6);


    }
    

    
}
