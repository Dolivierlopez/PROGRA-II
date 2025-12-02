package priorityQueue;

public class ArrayPriorityQueue<K extends Comparable<K>,V> implements PriorityQueue<K,V> {
		private int f;
		private int r;
		private int size;
		private Entrada<K,V>[] q;
		
		@SuppressWarnings("unchecked")
		public ArrayPriorityQueue() {
			f=0;
			r=0;
			size=0;
			q = (Entrada[]) new Entrada[100];
		}
		
		public void insert(K key, V value) throws MyException {
			/*
			Si size() == N-1 entonces 
				error( “cola llena”) 
			Sino 
				q[r] = e;
				r = (r+1) mod N

			 */
			if(size==q.length-1) {
				throw new MyException("Cola llena"); 
			}
			else
			{
				Entrada<K,V> Nuevo = new Entrada<K,V>(key,value);
				q[r] = Nuevo;
				size++;
				
				//SWAP
				int aux = r;
				int anterior;
				if(aux==0)
					anterior = q.length-1;
				else
					anterior = aux - 1;
				
				while(aux!=f && q[aux].getKey().compareTo(q[anterior].getKey())<0) {
					Entrada<K,V> auxiliar = q[anterior];
					q[anterior] = q[aux];
					q[aux]=auxiliar;
					aux=anterior;
					if(anterior==0)
						anterior = q.length-1;
					else
						anterior = aux - 1;
				}
				r = (r+1) % q.length;
			}
		}
		
		public Entry<K,V> removeMin() {
			/*
			Si isEmpty() entonces error( “cola vacía”)
	 		Sino
	 			temp = q[f];          
				q[f] = null; 
				f = (f+1) mod N;   
				retornar temp

			 */
			if(size==0)
				throw new MyException("Cola vacía");
			else
			{
				Entrada<K,V> temp = new Entrada<K,V>(q[f].getKey(),q[f].getValue());          
				q[f] = null; 
				f = (f+1) % q.length;
				size--;
				return temp;
			}
			
			
		}
		public Entry<K,V> min() {
			if(size==0)
				throw new MyException("Cola vacía");
			else
				return new Entrada<K,V>(q[f].getKey(),q[f].getValue());
		}
		
		public boolean isEmpty() {return size==0;}
		 
		public int size() {return size;}
}
