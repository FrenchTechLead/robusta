package org.javascool.compiler;

public class Jvs2Java {

	public static String translate(String jvsCode) {
		
		String text = jvsCode.replace((char) 160, ' ');
		
		StringBuilder body = new StringBuilder();
		
		body.append(text);		
		
		String finalBody = body.toString();//.replaceAll("(while.*\\{)", "$1 sleep(2);");
		//finalBody = finalBody.toString().replaceAll("(for.*\\{)", "$1 sleep(2);");
		String head = getHead().toString();
		return  head + finalBody + "\n}";
	}
	
	private static  StringBuilder getHead() {
		final String CLASS_NAME = "C";
		StringBuilder head = new StringBuilder();
		head.append("import static java.lang.Math.*;");
		head.append("import static org.javascool.macros.Stdin.*;");
		head.append("import static org.javascool.macros.Stdout.*;");
		head.append("import static org.javascool.compiler.Utils.*;");
		head.append("public class " + CLASS_NAME + "{");
		head.append("public static void main(String[] args) {");
		head.append("try{ main(); } catch(Exception e) { handleRuntimeExceptions(e); }");
		head.append("}\n");
		return head;
	}
}
