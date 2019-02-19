package org.javascool.macros;

import org.javascool.ui.Console;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Stdout {
	
	public static void print(String  o) {
		log.debug("print : " + o.toString());
		Console.output.appendText(o.toString());
	}
	
	public static void println(Object o) {
		log.debug("println : " + o.toString());
		Console.output.appendText(o.toString()+"\n");
	}
}
