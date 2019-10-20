package org.javascool.compiler;

import org.javascool.macros.Stdout;

public class RunTimeErrorHandler {
	
	public static void handle(Exception e) {
		int lineNumber = e.getStackTrace()[0].getLineNumber();
		StringBuilder sb = new StringBuilder();
		sb.append("Execution Error :\n");
		sb.append("Line number : " + lineNumber + "\n");
		sb.append(e.getMessage());
		Stdout.println(sb.toString());
	}
		

}
