package PilasColas;

import java.util.Stack;

public class PilasProblema_9_3 
{
    public static void main(String[] args) 
    {
        
    }
    
    public static  boolean verificarEquilibrio(String expresion)
    {
        Stack<String> pila = new Stack<>();

        for (int i = 0; i < expresion.length(); i++) 
        {
            String caracter = expresion.substring(i, i + 1);

            if(caracter.equals("(") || caracter.equals("[") || caracter.equals("{"))
                pila.push(caracter);
            else if(caracter.equals(")" || caracter.equals("]") || caracter.equals("}")))
                if(pila.isEmpty())
                    return false
        }
    }
}
