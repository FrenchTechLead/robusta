package org.robusta.compiler;

import java.io.StringWriter;

public class CompilationErrorHandler {
	public static void handle(StringWriter output, String jvsFilePath) {
		String errorStr = output.toString();
		String toPrint = "";
		String [] lines = errorStr.split("\r\n|\r|\n");
		for(int i = 0 ; i < lines.length ; i++) {
			String line = lines [i];
			if (line.contains("C.java")) {
				for(int j = i; j < lines.length; j++) {
					String line2 = lines [j];
					if(line2.contains("^")) {
						int columnNumber = line2.indexOf('^') + 1;
						line = handleLine(line, jvsFilePath, columnNumber);
						break;
					}
				}
				
			}
			toPrint = toPrint + line +  "\n";
		}
		
		printError(toPrint);
	}

	private static void printError(String errorStr) {
		StringBuilder sb = new StringBuilder();
		sb.append(errorStr);
		System.out.println(sb.toString());
	}
	
	private static String handleLine(String errorStr, String jvsFilePath, int columnNumber ) {
		String [] splices = errorStr.split(":");
		String lineNumber = splices[1];
		String rest = "";
		for(int i = 2; i < splices.length ; i++) {
			rest = rest + ":" + splices[i];
		}
		return jvsFilePath + ":" + lineNumber + "," + columnNumber + rest;
	}

}
