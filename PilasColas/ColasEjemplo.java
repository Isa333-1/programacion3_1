package PilasColas;

import java.util.LinkedList;
import java.util.Queue;

public class ColasEjemplo 
{
    public static void main(String[] args) 
    {
        Queue<String> colaImpresion = new LinkedList<>();

        colaImpresion.add("Imagenes.pdf");
        colaImpresion.add("Factura.docx");
        colaImpresion.add("Reporte.xlsx");
        colaImpresion.offer("Contrato.pdf");
        colaImpresion.offer("Despido.pdf");

        System.out.println("\nProximo documento a imprimir: " + colaImpresion.peek()
                            +"\nImprimiendo " + colaImpresion.poll() + " ..."
                            + "\nLos documentos que faltan por imprimir son: " + colaImpresion
        );
        System.out.println("\nImprimiendo el siguiente documento en la cola: " 
                            + colaImpresion.remove()
                            + "\nLos elementos que faltan por imprimir son: " + colaImpresion
        );
        System.out.println("\nImprimiendo el siguiente elemento en la cola: "
                            + colaImpresion.element()
                            + colaImpresion.remove()
                            + "\nLos elementos que faltan por imprimir son: " + colaImpresion
        );
    }
}
