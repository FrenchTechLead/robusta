package org.javascool.compiler;

import org.javascool.macros.Stdout;

public class RunTimeErrorHandler {
	
	public static void handle(Exception e) {
		int lineNumber = e.getStackTrace()[0].getLineNumber();
		/*
		String s = e.toString();
		String [] arr = s.split(":");
		
		String exceptionName = arr[0];
		
		switch (exceptionName) {
		case "java.lang.NumberFormatException":
			printException(s, "Le format du nombre est incorrect.", lineNumber);
			break;
		case "java.lang.ArithmeticException":
			String toPrint = "Erreur lors de l'opération arithmétique.";
			if(arr[1].equals(" / by zero"))
				toPrint+= " division par zéro impossible.";
			printException(s, toPrint, lineNumber);
			break;
		default:
			printException(s, exceptionName, lineNumber);
			break;
		}
		System.out.println(s);
		*/
		StringBuilder sb = new StringBuilder();
		sb.append("Execution Error :\n");
		sb.append("Line number : " + lineNumber + "\n");
		sb.append(e.getMessage());
		Stdout.println(sb.toString());
	}
		

}
