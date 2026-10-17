package ArrayList_ListTiendaFer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TiendaFer {
    public static void main(String[] args) 
    {
        List <String> compra = new ArrayList<>();
        List <String> promocion = new ArrayList<>();
        List<String> producto = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        
        producto.add("Aceite");
        producto.add("Arroz");
        producto.add("Azucar");
        producto.add("Sal");
        producto.add("Lentejas");
        producto.add("Frijoles");
        producto.add("Chocolate");
        producto.add("Cafe");
        producto.add("Croquetas");
        producto.add("Spaguetti");
        producto.add("Gelatina");
        producto.add("Alimento humedo");
        producto.add("Sardina");

        promocion.add("Sardina");
        promocion.add("Arroz");

        int opcion = 0;

        do {
            
            System.out.println("\n--------------- Bienvenido a TIENDA FER -------------");
            System.out.println("\n1. Agregar producto"
                                + "\n2. Ver carrito de compras"
                                + "\n3. Buscar producto de la tienda"
                                + "\n4. Verificar si un producto esta en el carrito"
                                + "\n5. Eliminar producto del carrito"
                                + "\n6. Reemplazar un producto del carrito"
                                + "\n7. Vaciar todo el carrito"
                                + "\n8. Visualizacion rápida del carrito"
                                + "\n9. Reclamar promocion"
                                + "\n10. Salir"
                                + "\nElige una opcion: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("--------------- PRODUCTOS --------------");
                                       
                    for (int i = 0; i < producto.size(); i++) 
                    {
                        System.out.println(producto.get(i));
                    }
                    System.out.println("\nEscribe el nombre del producto que quieres agregar");
                    
                    String productoSelec = sc.nextLine();

                    boolean encontrado = false;
    
                    for (int i = 0; i < producto.size(); i++) {
                        if (productoSelec.equalsIgnoreCase(producto.get(i))) 
                        {
                            encontrado = true;
                            break; // Detenemos el ciclo si ya lo encontramos
                        }
                    }
                    
                    if (encontrado) 
                    {
                        compra.add(productoSelec);
                        System.out.println("\n¡Producto agregado con exito!");
                    } else {
                        System.out.println("\nEl producto no esta disponible en la lista.");
                    }
                
                    break;
                case 2:
                    System.out.println("\n--- PRODUCTOS EN EL CARRITO ---");
                    if (compra.isEmpty()) { 
                        System.out.println("\nEl carrito esta vacio.");
                    } else {
                        System.out.println("\nTotal de productos: " + compra.size());
                        
                        for (int i = 0; i < compra.size(); i++) {
                            System.out.println((i + 1) + ". " + compra.get(i));
                        }
                    }
                    break;
                case 3:
                    System.out.print("\n¿Que producto de la tienda desea buscar?: ");
                    String busqueda = sc.nextLine();
                    encontrado = false;

                    for (int i = 0; i < producto.size(); i++) {
                        if (busqueda.equalsIgnoreCase(producto.get(i))) 
                        {
                            encontrado = true;
                            break; // Detenemos el ciclo si ya lo encontramos
                        }
                    }
                    
                    if (encontrado) 
                    {
                        System.out.println("\nEl producto si se vende en la tienda!"); 
                    } else {
                        System.out.println("\nEl producto no se vende en la tienda.");
                    }
                    break;
                case 4:
                    System.out.print("\nEscribe el producto que quieres verificar si esta en tu carrito: ");
                    String verificar = sc.nextLine();
                    
                    encontrado = false;
                    
                    for (int i = 0; i < producto.size(); i++) {
                        if (verificar.equalsIgnoreCase(producto.get(i))) 
                        {
                            encontrado = true;
                            break; // Detenemos el ciclo si ya lo encontramos
                        }
                    }

                    if (encontrado) 
                    {
                        System.out.println("\nEl producto que ingresaste SI se encuentra en tu carrito.");
                        System.out.println("\nNumero del producto en el carrito: " + (compra.indexOf(verificar) + 1));
                    } else {
                        System.out.println("\nEl producto NO esta en el carrito.");
                    }
                    break;
                case 5:
                    System.out.println("\n--- ELIMINAR PRODUCTO ---");
                    System.out.println("1. Por numero de posicion");
                    System.out.println("2. Por nombre del producto");
                    System.out.print("Elige una opcion: ");
                    int opcionEliminar = sc.nextInt();
                    sc.nextLine();

                    
                    switch (opcionEliminar) 
                    {
                        case 1:
                            System.out.println("\n------------ Productos del carrito ----------");
                            for (int i = 0; i < compra.size(); i++) 
                            {
                                System.out.println((i+1)+ ". " + compra.get(i));
                            }

                            System.out.print("\nIngresa el numero del producto a eliminar: ");
                            int pos = sc.nextInt();
                            if (pos > 0 && pos <= compra.size()) {
                                String eliminado = compra.remove(pos - 1); 
                                System.out.println("\nSe elimino correctamente: " + eliminado);
                            } else {
                                System.out.println("Posicion no valida.");
                            }
                            break;
                        case 2:
                            System.out.println("\n------------ Productos del carrito ----------");
                            for (int i = 0; i < compra.size(); i++) 
                            {
                                System.out.println((i+1)+ ". " + compra.get(i));
                            }

                            System.out.print("\nIngresa el nombre del producto que quieres eliminar: ");
                            String nombreEliminar = sc.nextLine();

                            encontrado = false;
                    
                            for (int i = 0; i < producto.size(); i++) {
                                if (nombreEliminar.equalsIgnoreCase(producto.get(i))) 
                                {
                                    encontrado = true;
                                    break; // Detenemos el ciclo si ya lo encontramos
                                }
                            }

                            if (encontrado) { 
                                compra.remove(nombreEliminar);
                                System.out.println("El producto fue eliminado de la lista.");
                            } else {
                                System.out.println("No se encontro ese producto.");
                            }
                            break;
                    }
                    break;
                case 6:
                    System.out.println("\n--- REEMPLAZAR PRODUCTO EN EL CARRITO ---");
    
                    if (compra.isEmpty()) {
                        System.out.println("\nEl carrito esta vacio, no hay productos para reemplazar");
                    } else {
                        System.out.println("\nProductos actuales en tu carrito:");
                        for (int i = 0; i < compra.size(); i++) {
                            System.out.println((i + 1) + ". " + compra.get(i));
                        }
                        
                        System.out.print("\nIngresa el nombre del producto que deseas reemplazar: ");
                        String productoActual = sc.nextLine();
                        

                        encontrado = false;
                        int indice = 0;
                    
                        for (int i = 0; i < compra.size(); i++) {
                            if (productoActual.equalsIgnoreCase(compra.get(i))) 
                            {
                                encontrado = true;
                                indice = i;
                                break; // Detenemos el ciclo si ya lo encontramos
                            }
                        }
                        
                        if (encontrado) {
                            System.out.print("\nEscribe el nuevo nombre del producto: ");
                            String nuevoProducto = sc.nextLine();

                            encontrado = false;
                    
                            for (int i = 0; i < producto.size(); i++) {
                                if (nuevoProducto.equalsIgnoreCase(producto.get(i))) 
                                {
                                    encontrado = true;
                                    break; // Detenemos el ciclo si ya lo encontramos
                                }
                            }

                            if (encontrado) 
                            {
                                String productoAnterior = compra.set(indice, nuevoProducto);
                                System.out.println("\n¡Exito! Se reemplazo '" + productoAnterior + "' por '" + nuevoProducto + "'.");
                            }
                            
                            
                        } else {
                            System.out.println("\nEl producto ingresado no se encuentra en tu carrito");
                        }
                    }
                    break;
                case 7:
                    compra.clear(); 
                    System.out.println("\n¡El carrito se ha vaciado por completo!");
                    break;
                case 8:
                    System.out.println("\n--- MOSTRAR CARRITO ---");
                    if (compra.isEmpty()) {
                        System.out.println("El carrito esta vacio.");
                    } else {
                        System.out.println("\nEl carrito tiene " + compra.size() + " productos" );
                        System.out.println("Contenido del carrito: " + compra.toString());
                    }
                    break;
                case 9:
                    System.out.println("\n------- Promocion -----------");
                    System.out.println("\n* Por compra de un arroz y una sardina lleva gratis un aceite");
                    boolean tieneTodo = compra.containsAll(promocion);
    
                    if (tieneTodo) {
                        System.out.println("\n¡Felicidades reclamaste el aceite gratis! Tu carrito incluye un arroz y una sardina ");
                        compra.add("Aceite");
                    } else {
                        System.out.println("\nNo tienes arroz y sardina en la lista, no puedes reclamar la promocion :c");
                    }
                    break;
                case 10:
                    System.out.println("\nGracias por usar nuestro programa :D");
                    break;
            
                default:
                    System.out.println("\nOpcion invalida ingrese la opcion de nuevo");
                    break;
            }
        } while (opcion != 10);
    }
}
