package Event;

public abstract class Event {
	protected int t;
	
	public Event(int time){
		t = time;
	}

	public int getT() {
		return t;
	}

	public abstract void processEvent(Object z, Object[] z_list);	
	
	@Override
	public String toString() {
		return "t="+t+";";
	}

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
