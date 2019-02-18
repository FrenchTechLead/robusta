package org.javascool.ui;

import org.javascool.compiler.JavaRuntimeCompiler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunRow {
	public static Label label1 = new Label("3");
	@Getter
	public static CustomButton runBtn;
	
	public static HBox getComponent(Scene scene) {
		
		
		runBtn = new CustomButton(scene, "Exécuter");
		runBtn.setDisable(true);
		runBtn.setOnAction( event -> {
			log.info("Running the code.");
			JavaRuntimeCompiler.run();
		});
		
		HBox hbox = new HBox(10);
	    hbox.setPadding(new Insets(15, 0, 0, 15));

	    hbox.getChildren().addAll(label1, runBtn);
	    
	    return hbox;
	}
}
