package org.javascool;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.javascool.compiler.Jvs2Java;

public class MainConsole {

	public static void main(String[] args) throws IOException {
		if (args.length > 0) {
			
			String path = args[0];
			if(verifyExtention(path)) {
				String fileContent= null;
				try {
					fileContent = readFile(path);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String javaCode = Jvs2Java.translate(fileContent);
				File file = new File("C.java");
				FileUtils.writeStringToFile(file, javaCode, Charset.defaultCharset());
				System.out.println("Compilation réussie.");
			} else {
				System.err.println("Le fichier doit avoir l'extension .jvs");
			}
			
		} else {
			System.err.println("Aucun fichier n'est spécifié.");
		}

	}

	public static String readFile(String path) throws IOException {
		File file = new File(path);
		return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
	}

	public static boolean verifyExtention(String path) {
		return path.contains(".jvs");

	}

}
