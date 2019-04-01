package org.javascool.macros;

import org.javascool.ui.Console;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Stdout {

	public static void print(Object o) {
		log.debug("print : " + o.toString());
		Console.getOutput().appendText(o.toString());

	}

	public static void println(Object o) {
		log.debug("println : " + o.toString());
		Console.getOutput().appendText(o.toString() + "\n");

	}

	public static void clear() {
		log.debug("clear");
		Console.getOutput().setText("");

	}
}
