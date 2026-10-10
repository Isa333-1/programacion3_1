package PilasColas;

import java.util.Stack;

public class PilasEjercicio_9_2 
{
    
    public static boolean secuencia(String cadena)
    {
        Stack<String> caracteres = new Stack<>();

        int separador = -1;
        for (int i = 0; i < cadena.length(); i++) 
        {
            if(cadena.substring(i, i + 1).equals("&")){
                separador = i;
                break;
            }    
        }

        if (separador == -1) 
        {
            return false;
        }

    }
}
