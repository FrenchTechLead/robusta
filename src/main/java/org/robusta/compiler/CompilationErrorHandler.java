package org.robusta.compiler;

import java.io.StringWriter;

import org.robusta.macros.Stdout;

public class CompilationErrorHandler {
	public static void handle(StringWriter output, String jvsFilePath) {

		String errorStr = output.toString();
		if (errorStr.contains("C.java")) {
			errorStr = putJvsFileInErrorStack(errorStr, jvsFilePath);
		}
		printError(errorStr);
	}

	private static void printError(String errorStr) {
		StringBuilder sb = new StringBuilder();
		sb.append("jvs_error:");
		sb.append(errorStr);
		Stdout.printError(sb.toString());
	}
	
	private static String putJvsFileInErrorStack(String errorStr, String jvsFilePath) {
		int i = errorStr.indexOf(":");
		
		return jvsFilePath + errorStr.substring(i);
	}

}
