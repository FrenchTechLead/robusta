package org.javascool.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;

public class Console {
	
	@Getter
	@Setter
	private static String consoleOutput= "apt-get install linux";
	
	public static VBox getComponent(Scene scene) {
		VBox vbox =  new VBox();
		vbox.getChildren().addAll(
				//getControlBtns(),
				getConsole()
				);
		return vbox;
	}
	
	private static HBox getControlBtns() {
		return null;
	}
	
	private static TextArea getConsole() {
		TextArea textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setPadding(new Insets(15, 15, 0, 15));
		textArea.setText(consoleOutput);
		textArea.getStyleClass().add("terminal");
		return textArea;
	}

}
