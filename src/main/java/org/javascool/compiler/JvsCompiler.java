package org.javascool.compiler;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

import org.apache.commons.io.FileUtils;
import org.javascool.macros.Stdout;

public class JvsCompiler {

	public static void build(String jvsFilePath) {

		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

		File parentDirectory = new File(System.getProperty("user.dir"));
		try {
			fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(parentDirectory));
		} catch (IOException e) {
			Stdout.printError(e.getMessage());
		}
		String jvsCode = readFile(jvsFilePath);
		String javaCode = translate(jvsCode);
		JavaSourceFromString jsfs = new JavaSourceFromString("C", javaCode);
		Iterable<? extends JavaFileObject> fileObjects = Arrays.asList(jsfs);
		StringWriter output = new StringWriter();
		boolean isCompilationSuccessful = compiler.getTask(output, fileManager, null, null, null, fileObjects).call();
		if (isCompilationSuccessful) {
			System.out.println("Compilation réussie");
		} else {
			CompilationErrorHandler.handle(output);
		}
		try {
			fileManager.close();
		} catch (IOException e) {
			Stdout.printError(e.getMessage());
		}
	}

	private static String translate(String jvsCode) {

		String text = jvsCode.replace((char) 160, ' ');

		StringBuilder body = new StringBuilder();

		body.append(text);

		String finalBody = body.toString();// .replaceAll("(while.*\\{)", "$1 sleep(2);");
		// finalBody = finalBody.toString().replaceAll("(for.*\\{)", "$1 sleep(2);");
		String head = getHead().toString();
		return head + finalBody + "\n}";
	}

	private static StringBuilder getHead() {
		final String CLASS_NAME = "C";
		StringBuilder head = new StringBuilder();
		head.append("import static java.lang.Math.*;");
		head.append("import static org.javascool.macros.Stdin.*;");
		head.append("import static org.javascool.macros.Stdout.*;");
		head.append("import static org.javascool.macros.Utils.*;");
		head.append("import static org.javascool.compiler.RunTimeErrorHandler.*;");
		head.append("public class " + CLASS_NAME + "{");
		head.append("public static void main(String[] args) {");
		head.append("try{ new C ().main(); } catch(Exception e) { handle(e); }");
		head.append("}\n");
		return head;
	}

	public static String readFile(String path) {
		File file = new File(path);
		String toReturn = null;
		try {
			toReturn = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		} catch (IOException e) {
			Stdout.printError("Impossible d'ouvrir le fichier : " + path);
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