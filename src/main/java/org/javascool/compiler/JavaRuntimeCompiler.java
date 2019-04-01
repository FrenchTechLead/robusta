package org.javascool.compiler;

import lombok.extern.slf4j.Slf4j;
import net.openhft.compiler.CachedCompiler;
import net.openhft.compiler.CompilerUtils;

@Slf4j
public class JavaRuntimeCompiler {

	
	private final String className = "org.javascool.JvsToJavaTranslated";
	private String javaCode;
	private static IMainWrapper runner;

	public JavaRuntimeCompiler(String javaCode) {
		this.javaCode = javaCode;
	}

	@SuppressWarnings("rawtypes")
	public void compile(int uid) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class aClass = null;
		runner = null;
		CachedCompiler compiler = null;
		compiler = CompilerUtils.CACHED_COMPILER;
		aClass = compiler.loadFromJava(this.className + uid, this.javaCode);
		runner = (IMainWrapper) aClass.newInstance();

	}

	public static void run() {
		if (runner != null) {
			runner.call();
		} else {
			log.error("Impossible de lancer le programme.");
		}

	}

}
