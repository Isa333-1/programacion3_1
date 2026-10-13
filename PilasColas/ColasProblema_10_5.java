package PilasColas;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ColasProblema_10_5 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
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

            System.out.print("Ingresa la entidad anunciada: ");
            String entidad = sc.nextLine();

            colaNSS.add(nss);
            colaEntidad.add(entidad);
            System.out.println("Registro diario agregado a la cola con éxito.");
        } while (true);

        if (!colaNSS.isEmpty()) 
        {
            do {
                String nssActual = colaNSS.poll();
                String entidadActual = colaEntidad.poll();

                boolean encontrado = false;

                // Recorremos la lista enlazada usando un ciclo for para verificar si ya existe el repartidor
                for (int i = 0; i < listaRepartidores.size(); i++) {
                    Repartidor rep = listaRepartidores.get(i);
                    if (rep.getNss().equals(nssActual)) {
                        // Si ya existe, actualizamos sus días trabajados y su última entidad
                        rep.incrementarDias();
                        rep.setEntidad(entidadActual);
                        encontrado = true;
                        break;
                    }
                }

                // Si el repartidor no estaba en la lista, lo agregamos como nuevo
                if (!encontrado) {
                    listaRepartidores.add(new Repartidor(nssActual, entidadActual, 1));
                }
            } while (!colaNSS.isEmpty());
        }

        System.out.println("\n----- LISTA ENLAZADA DE REPARTIDORES ACTUALIZADA -----");
        for (int i = 0; i < listaRepartidores.size(); i++) {
            System.out.println(listaRepartidores.get(i));
        }
    }
}
