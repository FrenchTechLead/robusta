package org.robusta.compiler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

import org.robusta.Main;


public class JarUtils {
	

	public static void create(File jvsFile) {
		try {
			String classPath = System.getProperty("java.class.path");
			String fileName = jvsFile.getName();
			int index = fileName.lastIndexOf('.');
			String generatedJarPath = jvsFile.getParent() + File.separator + fileName.substring(0, index)+ ".jar";
			Manifest manifest = getManifest();
			JarOutputStream jarOS = new JarOutputStream(new FileOutputStream(new File(generatedJarPath)), manifest);
			
			File dir = new File(".");
			File [] classFiles = dir.listFiles((File dirr, String name)-> name.endsWith(".class"));
			for (File classFile : classFiles) {
				byte[] classBytes = Files.readAllBytes(classFile.toPath());
				jarOS.putNextEntry(new JarEntry(classFile.getName()));
				jarOS.write(classBytes);
				jarOS.closeEntry();
			}

			
			JarFile jf = new JarFile(classPath);
			Enumeration<JarEntry> e = jf.entries();
			while(e.hasMoreElements()) {
				JarEntry je = e.nextElement();
				String entryName = je.getName();
				if(entryName.endsWith(".class") && entryName.startsWith("org/robusta")) {
					InputStream is = jf.getInputStream(je);
					byte [] bytes = new byte [is.available()];
					try {
						is.read(bytes);
						jarOS.putNextEntry(je);
						jarOS.write(bytes);
						jarOS.closeEntry();
					} catch(Exception re) {
						System.out.println(entryName);
					}
					
				}
			}
			jf.close();
			jarOS.close();
			for (File classFile : classFiles) {
				classFile.delete();
			}
			long COMPILATION_END_TIME = System.currentTimeMillis();
			System.out.println("Compilation Completed Successfully in (" + ((COMPILATION_END_TIME - Main.COMPILATION_START_TIME)) + " milliseconds).");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	private static Manifest getManifest() {
		Manifest manifest = new Manifest();
		Attributes main = manifest.getMainAttributes();
		main.putValue("Manifest-Version", "1.0");
		main.putValue("Main-Class", JvsCompiler.MAIN_CLASS_NAME);
		return manifest;
	}
}
