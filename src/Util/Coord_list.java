package Util;

import java.util.ArrayList;
import java.util.List;

import Graph.Coord;


public class Coord_list /*implements DeepCopy*/ {
/*	

	@Override
	public <E> List<E> DeepCopylist(int fromIndex, int toIndex, List<E> list){
		List<E> new_list = new ArrayList<E>(toIndex - fromIndex);
		for(E c : list) {
			if(((ArrayList<E>)list).indexOf(c) < fromIndex)
				continue;
			if(((ArrayList<E>)list).indexOf(c) == toIndex)
				break;
			else {
				Coord new_c = new Coord(((Coord) c).getX(), ((Coord) c).getY());
				new_list.add((E) new_c);
			}
		}
		return  new_list;

	}
*/
}

