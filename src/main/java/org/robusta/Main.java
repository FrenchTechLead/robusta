package org.robusta;

import org.robusta.compiler.JvsCompiler;
import org.robusta.macros.Stdout;

public class Main {

	private final static String COMPILE_GOAL = "compile";
	public final static long COMPILATION_START_TIME = System.currentTimeMillis();
	
	public static void main(String[] args) {
		if (args.length == 2) {
			handleActions(args);
		} else {
			Stdout.printError("Syntaxe error, command should be : java -jar robusta.jar compile file_path");
		}
	}

	private static void handleActions(String[] args) {
		String goal = args[0];
		switch (goal) {
		case COMPILE_GOAL:
			String javaFilePath = args[1];
			if (verifyExtention(javaFilePath)) {
				JvsCompiler.build(javaFilePath);
			} else {
				Stdout.printError("The file should have .jvs extension");
			}

			break;
		}
		
	}


	public static boolean verifyExtention(String path) {
		// TODO : verify extension efficiently.
		return path.contains(".jvs");

	}

}
