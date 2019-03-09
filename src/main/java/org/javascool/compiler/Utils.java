package org.javascool.compiler;

import org.javascool.ui.Console;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {
	
	public static void handleRuntimeExceptions(Exception e) {
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
			printException(s, "RuntimeException.", lineNumber);
			break;
		}
		log.error(s);
	}
	
	private static void printException(String s1, String s2, int lineNumber) {
		Console.getOutput().appendText( "\n-------Javascool Exception------- Ligne : " + lineNumber + "\n"+s2);
		Console.getOutput().appendText("\n\n-------Java Runtime Exception-------\n" + s1);
	}
	
	public static void sleep(int delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
