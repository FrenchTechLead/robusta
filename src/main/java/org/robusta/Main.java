package org.robusta;

import org.robusta.compiler.JvsCompiler;
import org.robusta.compiler.JvsFormatter;

public class Main {

	private final static String COMPILE_GOAL = "compile";
	private final static String FORMAT_GOAL = "format";
	public final static long COMPILATION_START_TIME = System.currentTimeMillis();
	
	public static void main(String[] args) {
		if (args.length == 2) {
			handleActions(args);
		} else {
			System.out.println("Syntaxe error, command should be : java -jar robusta.jar compile file_path");
		}
	}

	private static void handleActions(String[] args) {
		String goal = args[0];
		switch (goal) {
		case COMPILE_GOAL:
		case FORMAT_GOAL:
			String jvsFilePath = args[1];
			if (verifyExtention(jvsFilePath)) {
				if(goal.contentEquals(COMPILE_GOAL)) {
					JvsCompiler.build(jvsFilePath);
				} else if( goal.contentEquals(FORMAT_GOAL)) {
					JvsFormatter.format(jvsFilePath);
				}
				
			} else {
				System.out.println("The file should have .jvs extension");
			}

			break;
		} 
		
	}


	public static boolean verifyExtention(String path) {
		// TODO : verify extension efficiently.
		return path.contains(".jvs");

	}

}
