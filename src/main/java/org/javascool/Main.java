package org.javascool;

import org.javascool.compiler.JavaRuntimeCompiler;
import org.javascool.compiler.Jvs2Java;
import org.javascool.ui.JSFileChooser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

	public static void main(String[] args) throws Exception {
		String s = JSFileChooser.getFile().getFileContent();
		Jvs2Java jvs2java = new Jvs2Java();
		

		String javaCode =  jvs2java.translate(s);
		
		log.debug(javaCode);
		
		JavaRuntimeCompiler comp = new JavaRuntimeCompiler(javaCode);
		comp.compile();
		comp.run();
		
	}
}