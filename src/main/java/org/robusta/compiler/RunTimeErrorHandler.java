package org.robusta.compiler;

import org.robusta.macros.Stdout;

public class RunTimeErrorHandler {
	
	public static void handle(Exception e) {
		StringBuilder sb = new StringBuilder();
		int lineNumber = 0;
		StackTraceElement[] stack = e.getStackTrace();

		for(StackTraceElement trace: stack) {
			if(trace.getClassName().equals(JvsCompiler.MAIN_CLASS_NAME)) {
				lineNumber = trace.getLineNumber();
				break;
			}
		}
		
		sb.append("Execution Error :\n");
		sb.append("Line number : " + lineNumber + "\n");
		sb.append("Exception : " + e.getClass().getName() + "\n");
		sb.append("Message : " + e.getMessage());
		Stdout.println(sb.toString());
	}
		

}
