package ua.com.qalight.util;

import java.util.Random;

public class Randomizer {

	public static int getNumberInRange(int min, int max) {
		/*
		 * The first variant
		 */
		Random random = new Random();
		return min + random.nextInt(max - min);
		
		/*
		 * The second variant
		 * return min + (int)(Math.random()*(max-min));
		 */
	}

}
