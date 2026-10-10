package PilasColas;

import java.util.Stack;

public class PilasEjercicio_9_2 
{       
    public static void main(String[] args) 
    {
        System.out.println(secuencia("abc&cba"));
        System.out.println(secuencia("hola&hola"));
    }
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

        for (int i = 0; i < separador; i++) 
        {
            String x = cadena.substring(i, i+1);
            caracteres.push(x);
        }

        for (int i = separador +  1; i < cadena.length(); i++) 
        {
            if(caracteres.isEmpty()) 
                return false;

            String y = caracteres.pop();
            String caracter = cadena.substring(i, i+1);

            if(!y.equals(caracter))
                return false;
        }

        return caracteres.isEmpty();
    }

    
}
