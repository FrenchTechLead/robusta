package org.javascool.ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.lang3.StringUtils;
import org.javascool.compiler.JavaRuntimeCompiler;
import org.javascool.compiler.Jvs2Java;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CompileRow {

	public static JLabel label1 = new JLabel("2");
	@Getter
	public static CustomButton compileBtn;

	private static int uid = 1;

	private static ByteArrayOutputStream baos = new ByteArrayOutputStream();;
	private static PrintStream ps = new PrintStream(baos);

	public static JPanel getComponent() {

		System.setErr(ps);
		compileBtn = new CustomButton("Compiler");
		compileBtn.setEnabled(false);
		compileBtn.addActionListener((a) -> {
			baos.reset();
			String jvsCode = null;
			jvsCode = JSFileChooser.getFileContent();
			if (jvsCode != null && jvsCode != "") {
				uid++;
				log.debug("-----JVS-----\n" + jvsCode);
				String javaCode = Jvs2Java.translate(jvsCode, uid);
				log.debug("-----JAVA-----\n" + javaCode);
				JavaRuntimeCompiler jrc = new JavaRuntimeCompiler(javaCode);
				try {
					jrc.compile(uid);
					Console.getOutput().setText("Compilation Réussie.");
					RunRow.getRunBtn().setEnabled(true);
				} catch (Exception e) {
					String fullStr = baos.toString();
					log.error("Compilation Echouée.\n" + fullStr);
					reportCompilationError(fullStr);
					RunRow.getRunBtn().setEnabled(false);
				} finally {
				}

			}

		});

		JPanel p = new JPanel();
		p.add(label1);
		p.add(compileBtn);
		p.setVisible(true);
		return p;
	}

	private static void reportCompilationError(String fullStr) {

		StringBuilder sb = new StringBuilder("Compilation Echouée.\n\n\n");
		sb.append("***************************\n");

		final String[] lines = fullStr.split("\n");

		final String errorType = StringUtils.substringBetween(fullStr, "error:", "\n").trim();

		final String jvsLineStr = StringUtils.substringBetween(fullStr, "java:", ":").trim();

		final Integer jvsLine = new Integer(jvsLineStr) - 1;

		String errorTypeSimplified = "";

		switch (errorType) {
		case "not a statement":
			errorTypeSimplified = "La déclaration suivante est inconnue";
			break;
		case "cannot find symbol":
			errorTypeSimplified = "Le symbole / type suivant est inconnu";
			break;
		case "';' expected":
			errorTypeSimplified = "Il manque le symbole ;";
			break;
		case "reached end of file while parsing":
			errorTypeSimplified = "Il manque le symbole }";
			break;
		case "illegal start of expression":
			errorTypeSimplified = "Début d'expression illégal";
			break;
		default:
			errorTypeSimplified = errorType;
		}

		sb.append("Ligne :" + jvsLine + "\n");
		sb.append(errorTypeSimplified + " : \n");
		sb.append(lines[1] + "\n" + lines[2] + "\n");
		sb.append("***************************");
		Console.getOutput().setText(sb.toString());

	}
}
