import java.util.Scanner;

/** Punto de entrada para ejecutar cualquiera de los ejercicios de pilas y colas. */
public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = leerOpcion(entrada);
            entrada.nextLine(); // Consume el salto de linea de la opcion elegida.

            if (opcion != 0) {
                ejecutar(opcion);
                System.out.println();
            }
        } while (opcion != 0);

        System.out.println("Programa finalizado.");
    }

    private static void mostrarMenu() {
        System.out.println("\n========== PILAS Y COLAS ==========");
        System.out.println(" 1. Pilas 2  - Frases palindromas");
        System.out.println(" 2. Pilas 4  - Ordenar tomos");
        System.out.println(" 3. Pilas 5  - Expresion infija a postfija");
        System.out.println(" 4. Pilas 7  - Suma y resta de enteros grandes");
        System.out.println(" 5. Pilas 10 - Edicion de una linea");
        System.out.println(" 6. Colas 1  - Cola basica");
        System.out.println(" 7. Colas 4  - Cola circular enlazada");
        System.out.println(" 8. Colas 6  - Bicola (deque)");
        System.out.println(" 9. Colas 7  - Cola de cine");
        System.out.println("10. Colas 8  - Asignacion de consolas");
        System.out.println("11. Colas 9  - Asignacion de mesas");
        System.out.println(" 0. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    private static int leerOpcion(Scanner entrada) {
        while (!entrada.hasNextInt()) {
            System.out.print("Opcion no valida. Intente de nuevo: ");
            entrada.next();
        }
        return entrada.nextInt();
    }

    private static void ejecutar(int opcion) {
        String[] argumentos = new String[0];

        switch (opcion) {
            case 1 -> Pilas2.main(argumentos);
            case 2 -> Pilas4.main(argumentos);
            case 3 -> Pilas5.main(argumentos);
            case 4 -> Pilas7.main(argumentos);
            case 5 -> Pilas10.main(argumentos);
            case 6 -> Colas1.main(argumentos);
            case 7 -> Colas4.main(argumentos);
            case 8 -> Colas6.main(argumentos);
            case 9 -> Colas7.main(argumentos);
            case 10 -> Colas8.main(argumentos);
            case 11 -> Colas9.main(argumentos);
            default -> System.out.println("Opcion no valida.");
        }
    }
}
