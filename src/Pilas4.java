import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/** Ejercicio 4: ordena tomos extraidos de dos repisas tratadas como pilas. */
public class Pilas4 {
    private static Stack<Integer> leerRepisa(Scanner entrada, String nombre) {
        Stack<Integer> repisa = new Stack<>();
        System.out.print("Cantidad de tomos en la " + nombre + " repisa: ");
        int cantidad = entrada.nextInt();

        System.out.println("Ingrese los tomos de arriba hacia abajo:");
        List<Integer> tomos = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            tomos.add(entrada.nextInt());
        }
        // Al apilar de abajo hacia arriba, el primer tomo ingresado queda en el tope.
        for (int i = tomos.size() - 1; i >= 0; i--) {
            repisa.push(tomos.get(i));
        }
        return repisa;
    }

    public static List<Integer> ordenarTomos(Stack<Integer> primera, Stack<Integer> segunda) {
        List<Integer> terceraRepisa = new ArrayList<>();
        while (!primera.empty()) {
            terceraRepisa.add(primera.pop());
        }
        while (!segunda.empty()) {
            terceraRepisa.add(segunda.pop());
        }
        Collections.sort(terceraRepisa);
        return terceraRepisa;
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Stack<Integer> primera = leerRepisa(entrada, "primera");
        Stack<Integer> segunda = leerRepisa(entrada, "segunda");

        List<Integer> tercera = ordenarTomos(primera, segunda);
        System.out.println("Orden para colocar los tomos en la tercera repisa: " + tercera);
    }
}
