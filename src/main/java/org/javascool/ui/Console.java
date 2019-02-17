package org.javascool.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Console {

	public static volatile TextArea output = new TextArea();
	
	public static VBox getComponent(Scene scene) {
		initConsole();
		VBox vbox =  new VBox();
		vbox.getChildren().addAll(
				output,
				getControlBtns(scene)
				);
		vbox.setPadding(new Insets(15, 15, 0, 15));
		return vbox;
	}
	
	private static HBox getControlBtns(Scene scene) {
		HBox hbox = new HBox(20);
		CustomButton btnEffacer =  new CustomButton(scene, "Effacer");
		btnEffacer.setOnAction((e)-> output.setText(""));
		hbox.getChildren().add(btnEffacer);
		return hbox;
	}
	
	private static TextArea initConsole() {
		output.setEditable(false);
		output.setPrefHeight(500);
		output.getStyleClass().add("terminal");
		return output;
	}

}
