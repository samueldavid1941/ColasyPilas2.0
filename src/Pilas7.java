import java.util.Scanner;
import java.util.Stack;

/** Ejercicio 7: suma y resta enteros grandes almacenando cada digito en una pila. */
public class Pilas7 {
    private static Stack<Integer> crearPila(String numero) {
        if (!numero.matches("\\d+")) {
            throw new IllegalArgumentException("Solo se permiten numeros enteros no negativos.");
        }
        Stack<Integer> pila = new Stack<>();
        for (char digito : numero.toCharArray()) {
            pila.push(digito - '0');
        }
        return pila;
    }

    public static String sumar(String primerNumero, String segundoNumero) {
        Stack<Integer> primera = crearPila(primerNumero);
        Stack<Integer> segunda = crearPila(segundoNumero);
        Stack<Integer> resultado = new Stack<>();
        int acarreo = 0;

        while (!primera.empty() || !segunda.empty() || acarreo != 0) {
            int suma = acarreo + (primera.empty() ? 0 : primera.pop())
                    + (segunda.empty() ? 0 : segunda.pop());
            resultado.push(suma % 10);
            acarreo = suma / 10;
        }
        return convertirAString(resultado);
    }

    public static String restar(String primerNumero, String segundoNumero) {
        String a = quitarCeros(primerNumero);
        String b = quitarCeros(segundoNumero);
        int comparacion = comparar(a, b);
        if (comparacion == 0) {
            return "0";
        }
        boolean negativo = comparacion < 0;
        String mayor = negativo ? b : a;
        String menor = negativo ? a : b;
        Stack<Integer> primera = crearPila(mayor);
        Stack<Integer> segunda = crearPila(menor);
        Stack<Integer> resultado = new Stack<>();
        int prestamo = 0;

        while (!primera.empty()) {
            int diferencia = primera.pop() - prestamo - (segunda.empty() ? 0 : segunda.pop());
            if (diferencia < 0) {
                diferencia += 10;
                prestamo = 1;
            } else {
                prestamo = 0;
            }
            resultado.push(diferencia);
        }
        return (negativo ? "-" : "") + quitarCeros(convertirAString(resultado));
    }

    private static String convertirAString(Stack<Integer> pila) {
        StringBuilder numero = new StringBuilder();
        while (!pila.empty()) {
            numero.append(pila.pop());
        }
        return numero.toString();
    }

    private static String quitarCeros(String numero) {
        if (!numero.matches("\\d+")) {
            throw new IllegalArgumentException("Solo se permiten numeros enteros no negativos.");
        }
        return numero.replaceFirst("^0+(?!$)", "");
    }

    private static int comparar(String a, String b) {
        return a.length() != b.length() ? Integer.compare(a.length(), b.length()) : a.compareTo(b);
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Primer numero: ");
        String primero = entrada.nextLine();
        System.out.print("Segundo numero: ");
        String segundo = entrada.nextLine();
        System.out.print("Operacion (+ o -): ");
        String operacion = entrada.nextLine();
        try {
            if (operacion.equals("+")) {
                System.out.println("Resultado: " + sumar(primero, segundo));
            } else if (operacion.equals("-")) {
                System.out.println("Resultado: " + restar(primero, segundo));
            } else {
                System.out.println("Operacion no valida.");
            }
        } catch (IllegalArgumentException error) {
            System.out.println("Error: " + error.getMessage());
        }
    }
}
