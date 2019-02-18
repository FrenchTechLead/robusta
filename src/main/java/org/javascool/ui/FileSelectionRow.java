package org.javascool.ui;

import org.javascool.models.FileModel;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import lombok.extern.slf4j.Slf4j;
import javafx.scene.Scene;

@Slf4j
public class FileSelectionRow {
	
	public static FileModel selectedFile;
	public static Label label1 = new Label("1");
	public static Label label2 = new Label("Aucun fichier.");
	
	public static HBox getComponent(Scene scene) {
		
		CustomButton btn = new CustomButton(scene, "Ouvrir");
		btn.setOnAction( event -> {
			selectedFile = JSFileChooser.getFile();
			if(selectedFile != null) {
				String fileName = selectedFile.getFileName();
				label2.setText(fileName);
				log.info("Selected file : "+ fileName);
				CompileRow.getCompileBtn().setDisable(false);
				RunRow.getRunBtn().setDisable(true);
			}
				
		});
				
		HBox hbox = new HBox(10);
		hbox.setPadding(new Insets(15, 0, 0, 15));
	    
	    label2.setPrefSize(100, 20);
	    hbox.getChildren().addAll(label1, btn, label2);
	    
	    return hbox;
	}

}
