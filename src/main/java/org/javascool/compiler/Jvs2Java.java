package org.javascool.compiler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Jvs2Java {

	public static String translate(String jvsCode, int uid) {
		
		String text = jvsCode.replace((char) 160, ' ');
		String[] lines = text.split("\n");
		
		StringBuilder body = new StringBuilder();

		if (!text.replaceAll("[ \n\r\t]+", " ").matches(".*void[ ]+main[ ]*\\([ ]*\\).*")) {
			if (text.replaceAll("[ \n\r\t]+", " ").matches(".*main[ ]*\\([ ]*\\).*")) {
				log.error(
						"Attention: il faut mettre \"void\" devant \"main()\" pour que le programme puisse se compiler");
				text = text.replaceFirst("main[ ]*\\([ ]*\\)", "void main()");
			} else {
				log.error(
						"Attention: il faut un block \"void main()\" pour que le programme puisse se compiler");
				text = "\nvoid main() {\n" + text + "\n}\n";
			}
		}

		for (String line : lines) {
			body.append(line).append("\n");
		}
		
		String finalBody = body.toString().replaceAll("(while.*\\{)", "$1 sleep(1);");
		String head = getHead(uid).toString();
		return  head + finalBody + "\n\n" + "}";
	}
	
	private static  StringBuilder getHead(int uid) {
		StringBuilder head = new StringBuilder();
		head.append("import static java.lang.Math.*;");
		head.append("import static org.javascool.macros.Stdin.*;");
		head.append("import static org.javascool.macros.Stdout.*;");
		head.append("public class JvsToJavaTranslated"+uid).append(" implements Runnable{");
		head.append("  private static final long serialVersionUID = "+uid).append("L;");
		head.append("  public void run() {");
		head.append("   try{ main(); } catch(Throwable e) { ");
		head.append(
				"    if (e.toString().matches(\".*Interrupted.*\"))println(\"\\n-------------------\\nProgramme arrêté !\\n-------------------\\n\");");
		head.append(
				"    else println(\"\\n-------------------\\nErreur lors de l'exécution de la proglet-------------------\\n\");}");
		head.append("  }");
		return head;
	}
}
