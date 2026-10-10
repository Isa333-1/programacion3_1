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

        System.out.println("¿No hay ningun archivo para imprimir? " + colaImpresion.isEmpty()
                            + "\nLos documentos que hay en espera son: " + colaImpresion
        );

        System.out.println("\nProximo documento a imprimir: " + colaImpresion.peek()
                            +"\nImprimiendo " + colaImpresion.poll() + " ..."
                            + "\nLos documentos que faltan por imprimir son: " + colaImpresion
        );
        System.out.println("\nImprimiendo el siguiente documento en la cola: " 
                            + colaImpresion.remove()
                            + "\nLos elementos que faltan por imprimir son: " + colaImpresion
        );
        System.out.println("\nImprimiendo el siguiente documento en la cola: "
                            + colaImpresion.element()
                            + colaImpresion.remove()
                            + "\nLos documentos que faltan por imprimir son: " + colaImpresion
        );
        System.out.println("\nNumero de documentos en espera: " + colaImpresion.size());
        System.out.println("\n¿Hay algun documento en espera llamado TallerQuimica1.pdf? "
                            + "\nR// " + colaImpresion.contains("TallerQuimica1.pdf")
                            + "\n¿Hay algun elemento llamado Despido.pdf " 
                            + "\nR// " + colaImpresion.contains("Despido.pdf")
        );
        System.out.println("\nImprimiendo los ultimos documentos en espera...");
        colaImpresion.clear();
        System.out.println("Documentos en espera: " + colaImpresion.size());
    }
}
