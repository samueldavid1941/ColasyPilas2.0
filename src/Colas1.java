import java.util.Iterator;
import java.util.NoSuchElementException;

/** Ejercicio 1: TAD Cola con las operaciones adicionales solicitadas. */
public class Colas1<T> implements Iterable<T> {
    private static class Nodo<E> { E dato; Nodo<E> siguiente; Nodo(E d) { dato = d; } }
    private Nodo<T> primero, ultimo;
    private int tamano;

    public void agregar(T elemento) {
        Nodo<T> nuevo = new Nodo<>(elemento);
        if (estaVacia()) primero = nuevo; else ultimo.siguiente = nuevo;
        ultimo = nuevo; tamano++;
    }
    public T eliminar() {
        if (estaVacia()) throw new NoSuchElementException("La cola esta vacia.");
        T dato = primero.dato; primero = primero.siguiente; tamano--;
        if (primero == null) ultimo = null;
        return dato;
    }
    public boolean estaVacia() { return tamano == 0; }
    public int tamano() { return tamano; }
    public T ultimoElemento() {
        if (estaVacia()) throw new NoSuchElementException("La cola esta vacia.");
        return ultimo.dato;
    }
    public Colas1<T> alReves() {
        java.util.Stack<T> pila = new java.util.Stack<>();
        for (T e : this) pila.push(e);
        Colas1<T> inversa = new Colas1<>();
        while (!pila.empty()) inversa.agregar(pila.pop());
        return inversa;
    }
    /** Agrega al final los elementos de otra cola, sin modificarla. */
    public void concatenar(Colas1<T> otra) { for (T e : otra) agregar(e); }
    public static <E> Colas1<E> intercalar(Colas1<E> una, Colas1<E> otra) {
        Iterator<E> a = una.iterator(), b = otra.iterator(); Colas1<E> resultado = new Colas1<>();
        while (a.hasNext() || b.hasNext()) { if (a.hasNext()) resultado.agregar(a.next()); if (b.hasNext()) resultado.agregar(b.next()); }
        return resultado;
    }
    public Iterator<T> iterator() {
        return new Iterator<>() { Nodo<T> actual = primero;
            public boolean hasNext() { return actual != null; }
            public T next() { if (!hasNext()) throw new NoSuchElementException(); T d = actual.dato; actual = actual.siguiente; return d; }
        };
    }
    public String toString() { StringBuilder s = new StringBuilder("["); for (T e : this) { if (s.length() > 1) s.append(", "); s.append(e); } return s.append(']').toString(); }
    public static void main(String[] args) { Colas1<String> cola = new Colas1<>(); for (String x : new String[]{"A","B","C","D"}) cola.agregar(x); System.out.println("Cola: " + cola + ", ultimo: " + cola.ultimoElemento()); System.out.println("Inversa: " + cola.alReves()); }
}
