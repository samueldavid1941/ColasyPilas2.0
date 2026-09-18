import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Ejercicio 8: asigna consolas a las tiendas respetando el orden de solicitudes. */
public class Colas8 {
    record Consola(String codigo, String descripcion) { }
    record Solicitud(String tienda, int cantidad) { }
    record Asignacion(String tienda, List<String> codigos) { }
    public static List<Asignacion> asignar(ArrayDeque<Consola> inventario, ArrayDeque<Solicitud> solicitudes) {
        List<Asignacion> resultado = new ArrayList<>();
        while (!solicitudes.isEmpty()) { Solicitud s=solicitudes.removeFirst(); List<String> codigos=new ArrayList<>();
            for(int i=0;i<s.cantidad() && !inventario.isEmpty();i++) codigos.add(inventario.removeFirst().codigo());
            resultado.add(new Asignacion(s.tienda(), codigos)); }
        return resultado;
    }
    public static void main(String[] args) { Scanner e=new Scanner(System.in); ArrayDeque<Consola> inventario=new ArrayDeque<>(); ArrayDeque<Solicitud> solicitudes=new ArrayDeque<>(); System.out.print("Consolas disponibles: "); int n=e.nextInt(); e.nextLine(); for(int i=0;i<n;i++){System.out.print("Codigo: ");String c=e.nextLine();System.out.print("Descripcion: ");inventario.addLast(new Consola(c,e.nextLine()));} System.out.print("Solicitudes: ");n=e.nextInt();e.nextLine();for(int i=0;i<n;i++){System.out.print("Tienda: ");String t=e.nextLine();System.out.print("Cantidad: ");solicitudes.addLast(new Solicitud(t,e.nextInt()));e.nextLine();} for(Asignacion a:asignar(inventario,solicitudes))System.out.println(a.tienda()+": "+a.codigos()); }
}
