package tda;

// Implementación de Lista (modificada para acceso directo a campos)
public class ListaEnlazada<E> implements ListaTDA<E> {
    private NodoLista<E> origen;
    private int contador;

    public void InicializarLista() {
        origen = null;
        contador = 0;
    }

    // Agrega al inicio
    public void add(E x) { 
        NodoLista<E> nuevo = new NodoLista<E>();

        // --- INICIO DE LA CORRECCIÓN ---
        // Accedemos a los campos directamente en lugar de usar setters
        nuevo.info = x;      
        nuevo.sig = origen;  
        // --- FIN DE LA CORRECCIÓN ---

        origen = nuevo;
        contador++;
    }

    public int size() {
        return contador;
    }

    public boolean isEmpty() {
        return (contador == 0);
    }

    // Obtiene el elemento en la posición 'index'
    public E get(int index) {
        if (index < 0 || index >= contador) {
            return null; 
        }
        NodoLista<E> aux = origen;
        for(int i=0; i < index; i++) {
            aux = aux.sig; // Acceso directo
        }
        return aux.info; // Acceso directo
    }

    // Elimina la PRIMERA aparición de 'x'
    public void remove(E x) {
        if (isEmpty()) return;

        // Acceso directo a .info
        if (origen.info.equals(x)) { 
            origen = origen.sig; // Acceso directo
            contador--;
        } else { 
            NodoLista<E> aux = origen;
            // Acceso directo a .sig y .info
            while (aux.sig != null && !aux.sig.info.equals(x)) {
                aux = aux.sig;
            }
            if (aux.sig != null) { // Si lo encontró
                aux.sig = aux.sig.sig; // Acceso directo
                contador--;
            }
        }
    }
}