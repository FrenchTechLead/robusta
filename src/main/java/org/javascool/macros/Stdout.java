package org.javascool.macros;

import org.javascool.ui.Console;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Stdout {
	public static void println(String text) {
		log.debug("println : " + text);
		Console.output.setText(text);
	}
}
