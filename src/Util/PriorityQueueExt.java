package Util;

import java.util.Comparator;
import java.util.PriorityQueue;

import Individual.Individual;

public class PriorityQueueExt<E> extends PriorityQueue<E> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public PriorityQueueExt(Comparator<? super E> arg0) {
		super(arg0);
	}



	public PriorityQueueExt(int arg0, Comparator<? super E> arg1) {
		super(arg0, arg1);
	}


	public PriorityQueueExt<E> ChangeComparator(Comparator<E> c){
		PriorityQueueExt<E> aux_q = new PriorityQueueExt<E>(c);
		
		while(this.size() != 0) {
			aux_q.add(this.poll());
		}		
		return aux_q;		
		
	}


}
