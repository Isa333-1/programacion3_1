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
                            +"\nImprimiendo..." + colaImpresion.poll()
                            + "\nLos documentos que faltan por imprimir son: " + colaImpresion
    );
    }
}
