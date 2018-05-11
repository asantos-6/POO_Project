package PEC;

import java.util.Random;

public class ExpDistrib {

	private static Random random = new Random();
	
	public static double expRandom(double m) {
		double next = random.nextDouble();
		return -m*Math.log(1.0-next);
	}

}
