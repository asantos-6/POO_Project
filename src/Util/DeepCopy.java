package Util;

import java.util.List;

public interface DeepCopy<E> {
		
	public List<E> DeepCopylist(int fromIndex, int toIndex, List<E> list);
	
}
