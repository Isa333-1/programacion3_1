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
        System.out.println("Ingrese la frase para verificar si es palindromo");

        String frase = sc.nextLine();
        frase = frase.replace(" ", "").toLowerCase();

        for (int i = 0; i < frase.length(); i++) 
        {
            String caracter = frase.substring(i, i + 1);
            pila.push(caracter)
            cola.add(caracter);
        }
    }
    
}
