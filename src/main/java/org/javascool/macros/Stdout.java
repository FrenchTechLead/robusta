package org.javascool.macros;

public class Stdout {

	public static void print(Object o) {
		System.out.print(o.toString());
	}

	public static void println(Object o) {
		System.out.println(o.toString());

	}

	public static void clear() {
		for (int i = 0; i < 200; i++)
			System.out.println();

	}
	public static void printError(String str) {
		System.err.println(str);
	}
}
