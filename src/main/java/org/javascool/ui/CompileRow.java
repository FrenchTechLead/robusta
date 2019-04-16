package org.javascool.ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.swing.JLabel;
import javax.swing.JPanel;

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
					Console.getOutput().setText("Compilation Echouée.\n" + baos.toString());
					log.error("Compilation Echouée.\n" + baos.toString()+ baos.size());
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
}
