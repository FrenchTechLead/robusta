package org.robusta.compiler;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.nio.file.Files;
import java.util.Arrays;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

public class JvsCompiler {
	private static final File parentDirectory = new File(System.getProperty("user.dir"));
	
	public static void build(String jvsFilePath) {

		File jvsFile = new File(jvsFilePath);
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		if(compiler == null) {
			String currentJRE = System.getProperty("java.home");
			StringBuilder sb = new StringBuilder();
			sb.append("ERROR:\n");
			sb.append("The current process has been executed by the following JRE:\n");
			sb.append("> " + currentJRE + "\n");
			sb.append("Please make sure to run the program by a JDK and not a JRE.\n");
			sb.append("You should set the JAVA_HOME environnement variable to a valid JDK location.\n");
			sb.append("The following commands may help you to debug :\n");
			sb.append("> where javac (for windows) > which javac (for linux and mac)\n");
			sb.append("> where java  (for windows) > which java  (for linux and mac)\n");
			sb.append("> java -verbose\n");
			System.out.println(sb.toString());
		} else {
			StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

			try {
				fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(parentDirectory));
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			String jvsCode = readFile(jvsFile);
			String javaCode = translate(jvsCode);
			JavaSourceFromString jsfs = new JavaSourceFromString("C", javaCode);
			Iterable<? extends JavaFileObject> fileObjects = Arrays.asList(jsfs);
			StringWriter output = new StringWriter();
			boolean isCompilationSuccessful = compiler.getTask(output, fileManager, null, null, null, fileObjects).call();
			if (isCompilationSuccessful) {
				JarUtils.create(jvsFile);
			} else {
				CompilationErrorHandler.handle(output, jvsFilePath);
			}
			try {
				fileManager.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		
	}

	private static String translate(String jvsCode) {
		String text = jvsCode.replace((char) 160, ' ');
		StringBuilder body = new StringBuilder();
		body.append(text);
		String finalBody = body.toString();
		String head = getHead().toString();
		return head + finalBody + "}";
	}

	private static StringBuilder getHead() {
		final String CLASS_NAME = "C";
		StringBuilder head = new StringBuilder();
		head.append("import static java.lang.Math.*;");
		head.append("import static org.robusta.macros.Stdin.*;");
		head.append("import static org.robusta.macros.Stdout.*;");
		head.append("import static org.robusta.macros.Utils.*;");
		head.append("import static org.robusta.macros.IO.*;");
		head.append("import static org.robusta.compiler.RunTimeErrorHandler.*;");
		head.append("import org.robusta.macros.Console;");
		head.append("public class " + CLASS_NAME + "{");
		head.append("public static void main(String[] args) {");
		head.append("Console.getInstance();");
		head.append("try{ new C ().main(); } catch(Exception e) { handle(e); }");
		head.append("}");
		return head;
	}

	public static String readFile(File file) {
		String toReturn = null;
		try {
			toReturn = new String(Files.readAllBytes(file.toPath()));
		} catch (IOException e) {
			System.out.println("Can not find file : " + file.getAbsolutePath());
		}
		return toReturn;
	}
}

class JavaSourceFromString extends SimpleJavaFileObject {
	final String code;

	public JavaSourceFromString(String name, String code) {
		super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
		this.code = code;
	}

	@Override
	public CharSequence getCharContent(boolean ignoreEncodingErrors) {
		return code;
	}
}