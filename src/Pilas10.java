import java.util.Scanner;
import java.util.Stack;

/** Ejercicio 10: simula la edicion de una linea mediante una pila de caracteres. */
public class Pilas10 {
    public static String editarLinea(String entrada) {
        Stack<Character> linea = new Stack<>();
        for (char caracter : entrada.toCharArray()) {
            if (caracter == '-') {
                if (!linea.empty()) {
                    linea.pop();
                }
            } else if (caracter == '$' || caracter == '%') {
                // En esta simulacion el cursor esta al final de lo escrito; ambos comandos
                // eliminan el contenido actual de la linea, como indican los ejemplos.
                linea.clear();
            } else {
                linea.push(caracter);
            }
        }

        StringBuilder resultado = new StringBuilder();
        for (char caracter : linea) {
            resultado.append(caracter);
        }
        return resultado.toString();
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        String continuar;
        do {
            System.out.print("Dar una linea de texto: ");
            System.out.println("La linea editada es: " + editarLinea(entrada.nextLine()));
            System.out.print("Otra vez (s/n)? ");
            continuar = entrada.nextLine();
        } while (continuar.equalsIgnoreCase("s"));
    }
}
