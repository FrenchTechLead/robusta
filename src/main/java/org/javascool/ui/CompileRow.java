package org.javascool.ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.javascool.compiler.JavaRuntimeCompiler;
import org.javascool.compiler.Jvs2Java;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CompileRow {

	public static Label label1 = new Label("2");
	@Getter
	public static CustomButton compileBtn;

	private static int uid = 1;

	private static ByteArrayOutputStream baos;
	private static PrintStream ps;

	public static HBox getComponent(Scene scene) {
		
		PrintStream defaultSystemErr = System.err;
		compileBtn = new CustomButton(scene, "Compiler");
		compileBtn.setDisable(true);
		compileBtn.setOnAction(event -> {
			String jvsCode = null;
			jvsCode = JSFileChooser.getFm().getFileContent();
			if (jvsCode != null && jvsCode != "") {
				captureSystemErr();
				uid++;
				log.debug("-----JVS-----\n" + jvsCode);
				String javaCode = Jvs2Java.translate(jvsCode, uid);
				log.debug("-----JAVA-----\n" + javaCode);
				JavaRuntimeCompiler jrc = new JavaRuntimeCompiler(javaCode);
				try {
					jrc.compile(uid);
					Console.output.setText("Compilation Réussie.");
					RunRow.getRunBtn().setDisable(false);
				} catch (Exception e) {
					Console.output.setText("Compilation Echouée.\n" + baos.toString());
					log.error("Compilation Echouée.\n" + baos.toString());
					RunRow.getRunBtn().setDisable(true);
				} finally {
					resetSystemErr(defaultSystemErr);
				}
				

			}

		});

		HBox hbox = new HBox(10);
		hbox.setPadding(new Insets(15, 0, 0, 15));
		hbox.getChildren().addAll(label1, compileBtn);
		return hbox;
	}

	public static void captureSystemErr() {
		baos = new ByteArrayOutputStream();
		ps = new PrintStream(baos);
		System.setErr(ps);
	}

	public static void resetSystemErr(PrintStream defaultSystemErr) {
		System.setErr(defaultSystemErr);
	}
}
