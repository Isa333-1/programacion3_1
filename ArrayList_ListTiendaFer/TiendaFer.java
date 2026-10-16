package ArrayList_ListTiendaFer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TiendaFer 
{
    public static void main(String[] args) 
    {
        List <String> compra = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        
        int opcion = 0;

        do {
            
            System.out.println("--------------- Bienvenido a TIENDA FER -------------");
            System.out.println("1. Agregar producto"
                                + "2. Ver carrito de compras"
                                + "3. Buscar producto"
                                + "4. Verificar si existe un producto"
                                + "5. Eliminar producto por posicion o nombre"
                                + "6. Opciones avanzadas de memoria"
                                + "7. Vaciar todo el carrito"
                                + "8. Salir"
                                + "Elige una opcion: ");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("--------------- PRODUCTOS --------------"
                                        + "\nAceite "
                                        + "\nArroz"
                                        + "\nAzucar"
                                        + "\nSal"
                                        + "\nLentejas"
                                        + "\nFrijoles"
                                        + "\nChocolate"
                                        + "\nCafe"
                                        + "\nCroquetas"
                                        + "\nSpaguetti"
                                        + "\nGelatina"
                                        + "\nAlimento humedo"
                    );
                    System.out.print("Escribe el nombre del producto: ");
                    String producto = sc.nextLine();
                    compra.add(producto); 
                    System.out.println("¡Producto agregado con exito!");
                    break;
                case 2:
                    
                    break;
                case 3:
                    
                    break;
                case 4:
                    
                    break;
                case 5:
                    
                    break;
                case 6:
                    
                    break;
                case 7:
                    
                    break;
                case 8:
                    
                    break;
            
                default:
                    System.out.println("Opcion invalida ingrese la opcion de nuevo");
                    break;
            }
        } while (opcion != 7);
    }
}
