package Util;

import java.util.ArrayList;
import java.util.List;

import Graph.Coord;

public interface DeepCopy_interface {
	/*
	//INTERFACE!!!
	public <E> List<E> DeepCopylist(int fromIndex, int toIndex, List<E> list);
	public static List<Integer> DeepCopylist_Integer(int fromIndex, int toIndex, List<Integer> list) {
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
	List<Coord> DeepCopylist(int fromIndex, int toIndex, List<Coord> list);
	*/
}
