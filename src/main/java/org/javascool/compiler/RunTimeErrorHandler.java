package org.javascool.compiler;

public class RunTimeErrorHandler {
	
	public static void handle(Exception e) {
		int lineNumber = e.getStackTrace()[0].getLineNumber() - 1;
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
	}
	
	private static void printException(String s1, String s2, int lineNumber) {
		System.out.println( "\n-------Javascool Exception------- Ligne : " + lineNumber + "\n"+s2);
		System.out.println("\n\n-------Java Exception-------\n" + s1);
	}
	

}
