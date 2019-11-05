package org.robusta.macros;

public class Stdout {

	public static void print(Object o) {
		Console.getOutput().append(o.toString());
	}

	public static void println(Object o) {
		Console.getOutput().append(o.toString() + "\n");
	}
	
	public static void println() {
		Console.getOutput().append("\n");
	}

	public static void clear() {
		Console.getOutput().setText("");
	}

}

