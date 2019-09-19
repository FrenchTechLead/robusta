package org.javascool.compiler;

import java.io.StringWriter;

import org.apache.commons.lang3.StringUtils;
import org.javascool.macros.Stdout;

public class CompilationErrorHandler {
	public static void handle(StringWriter output) {

		String errorStr = output.toString();
		// Exception is not in jvsCode
		if (!errorStr.startsWith("/C.java")) {
			Stdout.printError("Erreur de copilation");
			Stdout.printError(errorStr);
		} else {
			Integer lineNumber = new Integer(StringUtils.substringBetween(errorStr, ":", ":")) - 1;
			printError(lineNumber, errorStr);
		}
	}

	private static void printError(int lineNumber, String errorStr) {
		StringBuilder sb = new StringBuilder();
		sb.append("Il y'a eu une erreur lors de la compilation.\n");
		sb.append("l'erreur se situe en ligne : " + lineNumber + "\n\n\n");
		sb.append("------Erreur Java-----\n");
		sb.append(errorStr);
		Stdout.printError(sb.toString());
	}

}
