package org.javascool.macros;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Stdout {
	public static void println(String text) {
		log.debug("println : " + text);
	}
}
