package Event;
import java.util.PriorityQueue;

import PEC.*;

public abstract class Event {
	protected int t;
	protected Object z;
	
	public Event(PEC pec, Object z) {
		this.z = z;		
		pec.addEvPEC(this);
	}

	public int getT() {
		return t;
	}

	public Object getZ() {
		return z;
	}

	public abstract <E> void processEvent(PriorityQueue<E> z_list, Object Simulation);

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
		Event other = (Event) obj;
		if (t != other.t)
			return false;
		return true;
	}
	
}
