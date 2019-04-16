package org.javascool.compiler;

import lombok.extern.slf4j.Slf4j;
import net.openhft.compiler.CachedCompiler;
import net.openhft.compiler.CompilerUtils;

@Slf4j
public class JavaRuntimeCompiler {

	private final String className = "org.javascool.JvsToJavaTranslated";
	private String javaCode;
	private static Runnable runnable;
	private static Thread thread;

	public JavaRuntimeCompiler(String javaCode) {
		this.javaCode = javaCode;
	}

	@SuppressWarnings("rawtypes")
	public void compile(int uid) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class aClass = null;
		runnable = null;
		CachedCompiler compiler = null;
		compiler = CompilerUtils.CACHED_COMPILER;
		aClass = compiler.loadFromJava(this.className + uid, this.javaCode);
		runnable = (Runnable) aClass.newInstance();

	}

	public static void doRun() {
		doStop();
		if (runnable != null) {
			(thread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						runnable.run();
						thread = null;
					} catch (Throwable e) {
						log.error(e.getMessage());
					}
				}
			})).start();
		}

	}

	public static void doStop(String message) {
		if (message != null) {
			System.out.println("Cause de l'interruption : " + message);
		}
		if (thread != null) {
			thread.interrupt();
			thread = null;
		}
	}

	public static void doStop() {
		doStop(null);
	}

}
