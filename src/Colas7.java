import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Scanner;

/** Ejercicio 7: cola de cine con prioridad para clientes asiduos. */
public class Colas7 {
    record Cliente(String nombre, boolean asiduo) { }
    private final ArrayDeque<Cliente> asiduos = new ArrayDeque<>();
    private final ArrayDeque<Cliente> ocasionales = new ArrayDeque<>();
    public void agregar(String nombre, boolean asiduo) { (asiduo ? asiduos : ocasionales).addLast(new Cliente(nombre, asiduo)); }
    public Cliente tomar() { if (estaVacia()) throw new NoSuchElementException("No hay clientes."); return !asiduos.isEmpty() ? asiduos.getFirst() : ocasionales.getFirst(); }
    public Cliente eliminar() { if (estaVacia()) throw new NoSuchElementException("No hay clientes."); return !asiduos.isEmpty() ? asiduos.removeFirst() : ocasionales.removeFirst(); }
    public int numeroOcasionales() { return ocasionales.size(); }
    public int numeroAsiduos() { return asiduos.size(); }
    public boolean estaVacia() { return asiduos.isEmpty() && ocasionales.isEmpty(); }
    public static void main(String[] args) { Scanner e = new Scanner(System.in); Colas7 cola = new Colas7(); System.out.print("Cantidad de clientes: "); int n=e.nextInt(); e.nextLine(); for(int i=0;i<n;i++){System.out.print("Nombre: ");String nombre=e.nextLine();System.out.print("Es asiduo (s/n): ");cola.agregar(nombre,e.nextLine().equalsIgnoreCase("s"));} while(!cola.estaVacia()) System.out.println("Atendiendo: "+cola.eliminar().nombre()); }
}
