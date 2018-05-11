package Util;

import java.util.ArrayList;
import java.util.List;

public class DeepCopy_Integer implements DeepCopy<Integer> {	

	@Override
	public List<Integer> DeepCopylist(int fromIndex, int toIndex, List<Integer> list) {
		List<Integer> new_list = new ArrayList<Integer>(toIndex - fromIndex);
		for(Integer i : list) {
			if(((ArrayList<Integer>)list).indexOf(i) < fromIndex)
				continue;
			if(((ArrayList<Integer>)list).indexOf(i) == toIndex)
				break;
			else {
				Integer new_i = Integer.valueOf(i);
				new_list.add(new_i);
			}
		}
		return new_list;
	}

}
