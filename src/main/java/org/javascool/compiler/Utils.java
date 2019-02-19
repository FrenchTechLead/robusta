package org.javascool.compiler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {

	public static void handleRuntimeExceptions(String s) {
		log.error(s);
	}
}
