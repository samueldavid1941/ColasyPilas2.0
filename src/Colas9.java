import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/** Ejercicio 9: asignacion de mesas por primera disponible o mejor ajuste. */
public class Colas9 {
    record Mesa(String codigo, int capacidad) { }
    record Reservacion(String nombre, int comensales) { }
    record Asignacion(String nombre, String mesa) { }
    public static List<Asignacion> asignar(ArrayDeque<Mesa> mesas, ArrayDeque<Reservacion> reservas, boolean optimizar) {
        List<Asignacion> resultado=new ArrayList<>();
        while(!reservas.isEmpty()) { Reservacion r=reservas.removeFirst(); Mesa elegida=null;
            if(optimizar) elegida=mesas.stream().filter(m->m.capacidad()>=r.comensales()).min(Comparator.comparingInt(Mesa::capacidad)).orElse(null);
            else for(Mesa m:mesas) if(m.capacidad()>=r.comensales()){elegida=m;break;}
            if(elegida!=null){mesas.remove(elegida);resultado.add(new Asignacion(r.nombre(),elegida.codigo()));} else resultado.add(new Asignacion(r.nombre(),"Sin mesa disponible")); }
        return resultado;
    }
    public static void main(String[] args) { Scanner e=new Scanner(System.in); ArrayDeque<Mesa> mesas=new ArrayDeque<>();ArrayDeque<Reservacion> reservas=new ArrayDeque<>();System.out.print("Numero de mesas: ");int n=e.nextInt();e.nextLine();for(int i=0;i<n;i++){System.out.print("Codigo: ");String c=e.nextLine();System.out.print("Capacidad: ");mesas.addLast(new Mesa(c,e.nextInt()));e.nextLine();}System.out.print("Reservaciones: ");n=e.nextInt();e.nextLine();for(int i=0;i<n;i++){System.out.print("Nombre: ");String nombre=e.nextLine();System.out.print("Comensales: ");reservas.addLast(new Reservacion(nombre,e.nextInt()));e.nextLine();}System.out.print("Optimizar ocupacion (s/n): ");boolean o=e.nextLine().equalsIgnoreCase("s");for(Asignacion a:asignar(mesas,reservas,o))System.out.println(a.nombre()+" -> "+a.mesa()); }
}
