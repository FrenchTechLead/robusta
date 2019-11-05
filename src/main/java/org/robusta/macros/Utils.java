package org.robusta.macros;

import java.util.Random;

public class Utils {
	public static void sleep(int delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean equal(String s1, String s2) {
		return s1.equals(s2);
	}
	
	public static int random(int i, int j) {
		Random r = new Random();
		return r.nextInt(j-i) + i;
	}
}
