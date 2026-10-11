package PilasColas;

import java.util.Scanner;
import java.util.Stack;

public class PilasProblema_9_4 
{
    public static void main(String[] args) 
    {
        Stack<Integer> p1 = new Stack<>();
        Stack<Integer> p2 = new Stack<>();
        Stack<Integer> p3 = new Stack<>();
        Stack<Integer> p4 = new Stack<>();
        Stack<Integer> p5 = new Stack<>();

        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese paras de numeros enteros (i,j), ingrese i = 0 para finalizar ");

        while (true) 
        {
            System.out.println("Ingrese i ");
            int i = sc.nextInt();

            if(i == 0)
            {
                System.out.println("Fin del proceso de entrada de datos");
                break;
            }

            if(Math.abs(i) > 5)
            {    
                System.out.println("El valor absoluto de i debe estar entre 1 y 5");
                continue;
            }

            System.out.println("Ingrese j ");
            int j = sc.nextInt();

            if(i > 0)
            {
                if(i == 1)
                    p1.push(j);
                else if(i == 2)
                    p2.push(j);
                else if (i == 3)
                    p3.push(j);
                else if(i == 4)
                    p4.push(j); 
                else if(i == 5)
                    p5.push(j);

            }else{
                int pilaAsig = Math.abs(i);
                boolean eliminado = false;
                int elementoRemovido = 0;

                if (pilaAsig == 1 && !p1.isEmpty()) 
                { 
                    elementoRemovido = p1.pop(); 
                    eliminado = true; 
                }
                else if (pilaAsig == 2 && !p2.isEmpty()) 
                { 
                    elementoRemovido = p2.pop(); 
                    eliminado = true; }
                else if (pilaAsig == 3 && !p3.isEmpty()) 
                { 
                    elementoRemovido = p3.pop(); 
                    eliminado = true; 
                }
                else if (pilaAsig == 4 && !p4.isEmpty()) 
                { 
                    elementoRemovido = p4.pop(); 
                    eliminado = true; 
                }
                else if (pilaAsig == 5 && !p5.isEmpty()) 
                { 
                    elementoRemovido = p5.pop(); 
                    eliminado = true; 
                }

                if (eliminado) 
                {
                    System.out.println("Eliminado el elemento " + elementoRemovido + " de la Pila P" + pilaAsig);
                } else {
                    System.out.println("La Pila P" + pilaAsig + " ya está vacía, no se puede eliminar.");
                }
            }
                
            System.out.println("pila 1: " + p1);
            System.out.println("pila 2: " + p2);
            System.out.println("pila 3: " + p3);
            System.out.println("pila 4: " + p4);
            System.out.println("pila 5: " + p5);
        }
    }
}
