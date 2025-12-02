package ABB;

public class DefaultComparator<E> implements java.util.Comparator<E> { 

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int compare( E a, E b ) { return((Comparable)a).compareTo(b); } 
}

