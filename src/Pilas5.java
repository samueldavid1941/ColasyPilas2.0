import java.util.Scanner;
import java.util.Stack;

/** Ejercicio 5: convierte una expresion aritmetica infija a postfija. */
public class Pilas5 {
    private static int prioridad(char operador) {
        return switch (operador) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            default -> -1;
        };
    }

    private static boolean esOperador(char caracter) {
        return prioridad(caracter) >= 0;
    }

    public static String aPostfija(String infija) {
        Stack<Character> pila = new Stack<>();
        StringBuilder salida = new StringBuilder();

        for (int i = 0; i < infija.length(); i++) {
            char actual = infija.charAt(i);
            if (Character.isWhitespace(actual)) {
                continue;
            }
            if (Character.isLetterOrDigit(actual)) {
                salida.append(actual).append(' ');
            } else if (actual == '(') {
                pila.push(actual);
            } else if (actual == ')') {
                while (!pila.empty() && pila.peek() != '(') {
                    salida.append(pila.pop()).append(' ');
                }
                if (pila.empty()) {
                    throw new IllegalArgumentException("Los parentesis no estan balanceados.");
                }
                pila.pop();
            } else if (esOperador(actual)) {
                while (!pila.empty() && pila.peek() != '('
                        && (prioridad(pila.peek()) > prioridad(actual)
                        || (prioridad(pila.peek()) == prioridad(actual) && actual != '^'))) {
                    salida.append(pila.pop()).append(' ');
                }
                pila.push(actual);
            } else {
                throw new IllegalArgumentException("Caracter no valido: " + actual);
            }
        }
        while (!pila.empty()) {
            if (pila.peek() == '(') {
                throw new IllegalArgumentException("Los parentesis no estan balanceados.");
            }
            salida.append(pila.pop()).append(' ');
        }
        return salida.toString().trim();
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Ingrese la expresion infija: ");
        try {
            System.out.println("Expresion postfija: " + aPostfija(entrada.nextLine()));
        } catch (IllegalArgumentException error) {
            System.out.println("Error: " + error.getMessage());
        }
    }
}
