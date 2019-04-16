package org.javascool.compiler;

public class Jvs2Java {

	public static String translate(String jvsCode, int uid) {
		
		String text = jvsCode.replace((char) 160, ' ');
		
		StringBuilder body = new StringBuilder();
		
		body.append(text);		
		
		String finalBody = body.toString();//.replaceAll("(while.*\\{)", "$1 sleep(2);");
		//finalBody = finalBody.toString().replaceAll("(for.*\\{)", "$1 sleep(2);");
		String head = getHead(uid).toString();
		return  head + finalBody + "\n}";
	}
	
	private static  StringBuilder getHead(int uid) {
		final String CLASS_NAME = "JvsToJavaTranslated"+uid;
		StringBuilder head = new StringBuilder();
		head.append("package org.javascool;");
		head.append("import static java.lang.Math.*;");
		head.append("import static org.javascool.macros.Stdin.*;");
		head.append("import static org.javascool.macros.Stdout.*;");
		head.append("import static org.javascool.compiler.Utils.*;");
		head.append("public class " + CLASS_NAME + " implements Runnable {");
		head.append("private static final long serialVersionUID = " + uid + "L;");
		head.append("@Override public void run() {");
		head.append("try{ main(); } catch(Exception e) { handleRuntimeExceptions(e); }");
		head.append("}\n");
		return head;
	}
}
