import java.util.Iterator;
import java.util.NoSuchElementException;

/** Ejercicio 4: Cola circular enlazada que reutiliza sus nodos al vaciarse. */
public class Colas4<T> implements Iterable<T> {
    private static class Nodo<E> { E dato; Nodo<E> siguiente; }
    private Nodo<T> primeroOcupado, primeroDisponible;
    private int tamano;
    public boolean estaVacia() { return tamano == 0; }
    public void vaciar() { while (!estaVacia()) eliminar(); }
    public void agregar(T elemento) {
        Nodo<T> nodo;
        if (primeroDisponible == null) { nodo = new Nodo<>(); nodo.siguiente = nodo; primeroDisponible = nodo; }
        nodo = primeroDisponible;
        if (nodo.siguiente == nodo) { primeroDisponible = null; } else { Nodo<T> anterior = nodo; while (anterior.siguiente != nodo) anterior = anterior.siguiente; anterior.siguiente = nodo.siguiente; primeroDisponible = nodo.siguiente; }
        nodo.dato = elemento;
        if (primeroOcupado == null) { primeroOcupado = nodo; nodo.siguiente = nodo; }
        else { Nodo<T> ultimo = primeroOcupado; while (ultimo.siguiente != primeroOcupado) ultimo = ultimo.siguiente; ultimo.siguiente = nodo; nodo.siguiente = primeroOcupado; }
        tamano++;
    }
    public T tomar() { if (estaVacia()) throw new NoSuchElementException("La cola esta vacia."); return primeroOcupado.dato; }
    public T eliminar() {
        T dato = tomar(); Nodo<T> eliminado = primeroOcupado;
        if (tamano == 1) primeroOcupado = null;
        else { Nodo<T> ultimo = primeroOcupado; while (ultimo.siguiente != primeroOcupado) ultimo = ultimo.siguiente; primeroOcupado = primeroOcupado.siguiente; ultimo.siguiente = primeroOcupado; }
        eliminado.dato = null;
        if (primeroDisponible == null) { eliminado.siguiente = eliminado; primeroDisponible = eliminado; }
        else { Nodo<T> ultimoDisp = primeroDisponible; while (ultimoDisp.siguiente != primeroDisponible) ultimoDisp = ultimoDisp.siguiente; ultimoDisp.siguiente = eliminado; eliminado.siguiente = primeroDisponible; primeroDisponible = eliminado; }
        tamano--; return dato;
    }
    public Iterator<T> iterator() { return new Iterator<>() { Nodo<T> actual = primeroOcupado; int vistos;
        public boolean hasNext() { return vistos < tamano; } public T next() { if (!hasNext()) throw new NoSuchElementException(); T d=actual.dato; actual=actual.siguiente; vistos++; return d; } }; }
    public static void main(String[] args) { Colas4<Integer> cola = new Colas4<>(); cola.agregar(1); cola.agregar(2); cola.agregar(3); for (int n : cola) System.out.print(n + " "); System.out.println("\nSale: " + cola.eliminar()); }
}
