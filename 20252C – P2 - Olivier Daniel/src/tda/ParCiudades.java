package tda;

// Clase auxiliar para guardar el 'par de ciudades' 
public class ParCiudades<E> {
    private E origen;
    private E destino;

    public ParCiudades(E o, E d) {
        this.origen = o;
        this.destino = d;
    }

    public E getOrigen() { return this.origen; }
    public E getDestino() { return this.destino; }

    // Sobreescribimos toString() para facilitar la impresión
    @Override
    public String toString() {
        return "(" + origen + " -> " + destino + ")";
    }
}