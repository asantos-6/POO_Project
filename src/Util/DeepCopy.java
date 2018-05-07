package Util;

import java.util.ArrayList;
import java.util.List;

import Graph.Coord;

public class DeepCopy {

	public static List<Coord> DeepCopylist_Coord(int fromIndex, int toIndex, List<Coord> list) {
		List<Coord> new_list = new ArrayList<Coord>(toIndex - fromIndex);
		for(Coord c : list) {
			if(((ArrayList<Coord>)list).indexOf(c) < fromIndex)
				continue;
			if(((ArrayList<Coord>)list).indexOf(c) == toIndex)
				break;
			else {
				Coord new_c = new Coord(c.getX(), c.getY());
				new_list.add(new_c);
			}
		}
		return new_list;
	}
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

}
