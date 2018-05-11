package Util;

import java.util.ArrayList;
import java.util.List;

import Graph.Coord;


public class DeepCopy_Coord implements DeepCopy<Coord> {

	@Override
	public List<Coord> DeepCopylist(int fromIndex, int toIndex, List<Coord> list) {
		List<Coord> new_list = new ArrayList<Coord>(toIndex - fromIndex);
		for(Coord c : list) {
			if(((ArrayList<Coord>)list).indexOf(c) < fromIndex)
				continue;
			if(((ArrayList<Coord>)list).indexOf(c) == toIndex)
				break;
			else {
				Coord new_c = new Coord(((Coord) c).getX(), ((Coord) c).getY());
				new_list.add(new_c);
			}
		}
		return  new_list;
	}

}

