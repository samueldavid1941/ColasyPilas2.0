import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** Ejercicio 6: TAD Deque o bicola. */
public class Colas6<T> implements Iterable<T> {
    private final ArrayDeque<T> datos = new ArrayDeque<>();
    public void agregar(T e) { datos.addLast(e); }
    public void agregarPrimero(T e) { datos.addFirst(e); }
    public T eliminar() { if (datos.isEmpty()) throw new NoSuchElementException("Deque vacio."); return datos.removeFirst(); }
    public T eliminarUltimo() { if (datos.isEmpty()) throw new NoSuchElementException("Deque vacio."); return datos.removeLast(); }
    public T tomar() { if (datos.isEmpty()) throw new NoSuchElementException("Deque vacio."); return datos.getFirst(); }
    public T ultimoElemento() { if (datos.isEmpty()) throw new NoSuchElementException("Deque vacio."); return datos.getLast(); }
    public boolean estaVacia() { return datos.isEmpty(); }
    public Iterator<T> iterator() { return datos.iterator(); }
    public Iterator<T> iteradorEnReversa() { return datos.descendingIterator(); }
    public static void main(String[] args) { Colas6<Integer> d = new Colas6<>(); d.agregar(2); d.agregar(3); d.agregarPrimero(1); System.out.println("Primero=" + d.tomar() + ", ultimo=" + d.ultimoElemento()); d.iteradorEnReversa().forEachRemaining(x -> System.out.print(x + " ")); }
}
