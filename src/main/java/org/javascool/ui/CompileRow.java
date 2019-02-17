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

	private static PrintStream defaultSystemErr = System.err;

	private static ByteArrayOutputStream baos;
	private static PrintStream ps;

	public static HBox getComponent(Scene scene) {

		compileBtn = new CustomButton(scene, "Compiler");
		compileBtn.setDisable2(true);
		compileBtn.setOnAction(event -> {
			String jvsCode = null;
			jvsCode = JSFileChooser.getFm().getFileContent();
			if (jvsCode != null && jvsCode != "") {
				redirectSystemErr();
				uid++;
				log.debug("-----JVS-----\n" + jvsCode);
				String javaCode = Jvs2Java.translate(jvsCode, uid);
				log.debug("-----JAVA-----\n" + javaCode);
				JavaRuntimeCompiler jrc = new JavaRuntimeCompiler(javaCode);
				try {
					jrc.compile(uid);
					Console.output.setText("Compilation Réussie.");
				} catch (Exception e) {
					System.out.flush();
					Console.output.setText("Compilation Echouée.\n" + baos.toString());
				}
				baos.reset();
				resetSystemErr();
				RunRow.getRunBtn().setDisable2(false);

			}

		});

		HBox hbox = new HBox(10);
		hbox.setPadding(new Insets(15, 0, 0, 15));

		hbox.getChildren().addAll(label1, compileBtn);

		return hbox;
	}

	public static void redirectSystemErr() {
		baos = new ByteArrayOutputStream();
		ps = new PrintStream(baos);
		System.setErr(ps);
	}

	public static void resetSystemErr() {
		System.setErr(defaultSystemErr);
	}
}
