package org.javascool.compiler;

import lombok.extern.slf4j.Slf4j;
import net.openhft.compiler.CachedCompiler;
import net.openhft.compiler.CompilerUtils;

@Slf4j
public class JavaRuntimeCompiler {
	
	private final String packagePrefix = "package org.javascool;";
	private final String className = "org.javascool.JvsToJavaTranslated";
	private String javaCode;
	private static Runnable runner;
	
	public JavaRuntimeCompiler(String javaCode) {
		this.javaCode = packagePrefix + javaCode;
	}
	
	@SuppressWarnings("rawtypes")
	public void compile(int uid) {
		log.info("Compilation en crous ...");
		Class aClass = null;
		runner = null;
		
		try {
			CachedCompiler compiler = CompilerUtils.CACHED_COMPILER;
			compiler.close();
			aClass = compiler.loadFromJava(this.className+uid, this.javaCode);
			
		} catch (ClassNotFoundException e) {
			log.info("Compilation echouée.");
			log.error(e.getMessage());
		}
		try {
			runner = (Runnable) aClass.newInstance();
			log.info("Compilation Réussie.");
		} catch (InstantiationException | IllegalAccessException e) {
			log.info("Compilation echouée.");
			log.error(e.getMessage());
		}
	}
	
	public static void run() {
		if(runner != null) {
			runner.run();
		} else {
			log.error("Impossible de lancer le programme.");
		}
		
	}

}
