package Event;
import java.util.PriorityQueue;

public abstract class Event<E>{
	protected int t;
	protected Object z;
	
	public Event(Object z) {
		this.z = z;	
	}

	public int getT() {
		return t;
	}

	public Object getZ() {
		return z;
	}

	public abstract void processEvent(PriorityQueue<E> z_list, Object Simulation);

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + t;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event<E> other = (Event<E>) obj;
		if (t != other.t)
			return false;
		return true;
	}
	
	
}
