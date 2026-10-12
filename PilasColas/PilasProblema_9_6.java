package PilasColas;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class PilasProblema_9_6 
{
    public static void main(String[] args) 
    {
        Stack<String> pila = new Stack<>();
        Queue<String> cola = new LinkedList<>();

        Scanner sc = new Scanner(System.in);
        System.out.println("\nIngrese la frase para verificar si es palindromo");

        String frase = sc.nextLine();
        frase = frase.replace(" ", "").toLowerCase();

        for (int i = 0; i < frase.length(); i++) 
        {
            String caracter = frase.substring(i, i + 1);
            pila.push(caracter);
            cola.add(caracter);
        }

        boolean palindromo = true;

        for (int i = 0; i < frase.length(); i++) 
        {
            String caracterPila = pila.pop();
            String caracterCola = cola.poll();

            if(!caracterPila.equals(caracterCola))
            {
                palindromo = false;
                break;
            }
        }

        if (palindromo) 
            System.out.println("\nLa frase es un palindromo");
        else
            System.out.println("\nLa frase no es un palindromo");
    }
    
}
