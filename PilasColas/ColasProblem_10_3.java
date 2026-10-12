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
        Queue<String> caja1 = new LinkedList<>();
        Queue<String> caja2 = new LinkedList<>();
        Queue<String> caja3 = new LinkedList<>();

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
                        break;
                    }
                       
                    String clienteAsig = "El cliente numero " + cliente + " tiene el carrito numero " + carritoCompra.poll();
                    

                    if (caja1.size() <= caja2.size() && caja1.size() <= caja3.size()) 
                    {
                        caja1.add(clienteAsig);
                        System.out.println("El cliente numero " + cliente + " se ha ubicado en la CAJA 1.");
                    } else if (caja2.size() <= caja1.size() && caja2.size() <= caja3.size()) {
                        caja2.add(clienteAsig);
                        System.out.println("El cliente numero " + cliente + " se ha ubicado en la CAJA 2.");
                    } else {
                        caja3.add(clienteAsig);
                        System.out.println("El cliente numero " + cliente + " se ha ubicado en la CAJA 3.");
                    }

                    cliente++;
                    break;
                case 2:
                    System.out.print("¿De qué caja sale el cliente? ");
                    int numCaja = sc.nextInt();
                    String clienteAtendido = null;

                    if (numCaja == 1 && !caja1.isEmpty()) {
                        clienteAtendido = caja1.poll();
                    } else if (numCaja == 2 && !caja2.isEmpty()) {
                        clienteAtendido = caja2.poll();
                    } else if (numCaja == 3 && !caja3.isEmpty()) {
                        clienteAtendido = caja3.poll();
                    }

                    if (clienteAtendido != null) {
                        System.out.println(clienteAtendido + " ha pagado y salido del supermercado.");
                        int carritoLiberado = (int)(Math.random() * 25) + 1; 
                        carritoCompra.add(carritoLiberado);
                        System.out.println("El carrito ha quedado disponible nuevamente.");
                    } else {
                        System.out.println("La caja seleccionada esta vacia.");
                    }
                    break;
                case 3:
                    System.out.println("Carritos disponibles en stock: " + carritoCompra.size());
                    break;
                case 4:
                    System.out.println("Caja 1: " + caja1);
                    System.out.println("Caja 2: " + caja2);
                    System.out.println("Caja 3: " + caja3);
                    break;
                case 5:
                    System.out.println("Gracias por usar nuestro programa :D");
                    break;
            
                default:
                    System.out.println("Opcion invalida intente de nuevo");
                    break;
            }
            
        } while (opcion != 5);

    }
    

    
}
