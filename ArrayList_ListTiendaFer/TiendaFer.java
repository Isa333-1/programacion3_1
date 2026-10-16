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
        } while (opcion != 7);
    }
}
