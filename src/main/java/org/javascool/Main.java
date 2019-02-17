package org.javascool;

import org.javascool.compiler.JavaRuntimeCompiler;
import org.javascool.compiler.Jvs2Java;
import org.javascool.ui.JSFileChooser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

	public static void main(String[] args) throws Exception {
		String s = JSFileChooser.getFile().getFileContent();
	
		String javaCode =  Jvs2Java.translate(s, 1);
		
		log.debug(javaCode);
		
		JavaRuntimeCompiler comp = new JavaRuntimeCompiler(javaCode);
		comp.compile(1);
		JavaRuntimeCompiler.run();
		
	}
}