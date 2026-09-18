import java.text.Normalizer;
import java.util.Scanner;
import java.util.Stack;

/** Ejercicio 2: verifica frases palindromas usando una pila. */
public class Pilas2 {
    public static boolean esPalindroma(String texto) {
        String limpia = Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .replaceAll("[^a-zA-Z0-9]", "")
                .toLowerCase();

        Stack<Character> pila = new Stack<>();
        for (char caracter : limpia.toCharArray()) {
            pila.push(caracter);
        }

        for (int i = 0; i < limpia.length(); i++) {
            if (limpia.charAt(i) != pila.pop()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Ingrese una palabra o frase: ");
        String texto = entrada.nextLine();

        System.out.println(esPalindroma(texto)
                ? "La frase es palindroma."
                : "La frase no es palindroma.");
    }
}
