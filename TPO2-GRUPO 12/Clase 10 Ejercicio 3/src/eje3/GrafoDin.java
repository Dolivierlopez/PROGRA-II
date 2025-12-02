package eje3;

import java.util.LinkedList;

public class GrafoDin<E> implements GrafoTDA<E> {
	private NodoVertice<E> origen;
	private int vertices;
	
	public GrafoDin() {
		origen = null;
		vertices = 0;
	}
	
	public void agregarVertice(E v) {
		NodoVertice<E> aux = new NodoVertice<E>();
		aux.setVertice(v);
		aux.setAristas(null);
		aux.setSigVertice(origen);
		origen = aux;
		vertices++;
	}
	
	public void eliminarVertice(E v) {
		if (origen == null) return;
		
		if (origen.getVertice().equals(v))
			origen = origen.getSigVertice();
		
		NodoVertice<E> aux = origen;
		while (aux != null) {
			eliminarAristaNodo(aux, v);
			if (aux.getSigVertice() != null && aux.getSigVertice().getVertice().equals(v)) {
				aux.setSigVertice(aux.getSigVertice().getSigVertice());
				vertices--;
			}
			aux = aux.getSigVertice();
		}
	}
	
	private void eliminarAristaNodo(NodoVertice<E> nodo, E v) {
		NodoArista<E> aux = nodo.getAristas();
		if (aux != null) {
			if (aux.getVerticeDestino().getVertice().equals(v)) {
				nodo.setAristas(aux.getSigArista());
			} else {
				while (aux.getSigArista() != null && 
					  !aux.getSigArista().getVerticeDestino().getVertice().equals(v))
					aux = aux.getSigArista();
				if (aux.getSigArista() != null)
					aux.setSigArista(aux.getSigArista().getSigArista());
			}
		}
	}
	
	public E[] vertices() {
		@SuppressWarnings("unchecked")
		E[] salida = (E[]) new Object[vertices];
		NodoVertice<E> aux = origen;
		int i = 0;
		while (aux != null) {
			salida[i++] = aux.getVertice();
			aux = aux.getSigVertice();
		}
		return salida;
	}
	
	public void agregarArista(E v1, E v2, int peso) {
		NodoVertice<E> n1 = vert2Nodo(v1);
		NodoVertice<E> n2 = vert2Nodo(v2);
		if (n1 == null || n2 == null) return;
		
		NodoArista<E> nueva = new NodoArista<E>();
		nueva.setPeso(peso);
		nueva.setVerticeDestino(n2);
		nueva.setSigArista(n1.getAristas());
		n1.setAristas(nueva);
	}
	
	private NodoVertice<E> vert2Nodo(E v) {
		NodoVertice<E> aux = origen;
		while (aux != null && !aux.getVertice().equals(v))
			aux = aux.getSigVertice();
		return aux;
	}
	
	public void eliminarArista(E v1, E v2) {
		NodoVertice<E> n1 = vert2Nodo(v1);
		if (n1 != null)
			eliminarAristaNodo(n1, v2);
	}
	
	public boolean existeArista(E v1, E v2) {
		NodoVertice<E> n1 = vert2Nodo(v1);
		if (n1 == null) return false;
		
		NodoArista<E> aux = n1.getAristas();
		while (aux != null && !aux.getVerticeDestino().getVertice().equals(v2))
			aux = aux.getSigArista();
		return aux != null;
	}
	
	public int pesoArista(E v1, E v2) {
		NodoVertice<E> n1 = vert2Nodo(v1);
		if (n1 == null) return -1;
		
		NodoArista<E> aux = n1.getAristas();
		while (aux != null && !aux.getVerticeDestino().getVertice().equals(v2))
			aux = aux.getSigArista();
		return (aux != null) ? aux.getPeso() : -1;
	}
		
	// vertices aislados
	public LinkedList<E> verticesAislados() {
		LinkedList<E> aislados = new LinkedList<E>();
		NodoVertice<E> actual = origen;

		while (actual != null) {
			boolean tieneEntrantes = false;
			boolean tieneSalientes = (actual.getAristas() != null);

			NodoVertice<E> aux = origen;
			while (aux != null && !tieneEntrantes) {
				NodoArista<E> arista = aux.getAristas();
				while (arista != null && !tieneEntrantes) {
					if (arista.getVerticeDestino().getVertice().equals(actual.getVertice()))
						tieneEntrantes = true;
					arista = arista.getSigArista();
				}
				aux = aux.getSigVertice();
			}

			if (!tieneEntrantes && !tieneSalientes)
				aislados.add(actual.getVertice());

			actual = actual.getSigVertice();
		}
		return aislados;
	}
	
	// vertices puente entre o y d
	public LinkedList<E> verticesPuente(E o, E d) {
		LinkedList<E> puentes = new LinkedList<E>();
		NodoVertice<E> origenNodo = vert2Nodo(o);
		if (origenNodo == null) return puentes;

		NodoArista<E> arista = origenNodo.getAristas();
		while (arista != null) {
			NodoVertice<E> posiblePuente = arista.getVerticeDestino();
			NodoArista<E> arista2 = posiblePuente.getAristas();
			while (arista2 != null) {
				if (arista2.getVerticeDestino().getVertice().equals(d))
					puentes.add(posiblePuente.getVertice());
				arista2 = arista2.getSigArista();
			}
			arista = arista.getSigArista();
		}
		return puentes;
	}
}
