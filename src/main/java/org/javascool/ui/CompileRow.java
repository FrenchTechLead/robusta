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

	private static ByteArrayOutputStream baos = new ByteArrayOutputStream();;
	private static PrintStream ps = new PrintStream(baos);
	

	public static HBox getComponent(Scene scene) {
		
		System.setErr(ps);
		compileBtn = new CustomButton(scene, "Compiler");
		compileBtn.setDisable(true);
		compileBtn.setOnAction(event -> {
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
					RunRow.getRunBtn().setDisable(false);
				} catch (Exception e) {
					Console.getOutput().setText("Compilation Echouée.\n" + baos.toString());
					log.error("Compilation Echouée.\n" + baos.toString()+ baos.size());
					RunRow.getRunBtn().setDisable(true);
				} finally {
				}
				

			}

		});

		HBox hbox = new HBox(10);
		hbox.setPadding(new Insets(15, 0, 0, 15));
		hbox.getChildren().addAll(label1, compileBtn);
		return hbox;
	}
}
