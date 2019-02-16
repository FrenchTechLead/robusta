package org.javascool.ui;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class CustomButton extends Button {

	CustomButton(Scene scene, String Btntext) {
		super();
		this.setText(Btntext);
		this.setOnMouseEntered((x)-> scene.setCursor(Cursor.HAND));
		this.setOnMouseExited((x)-> scene.setCursor(Cursor.DEFAULT));
		this.getStyleClass().add("btn");
	    this.setPrefSize(100, 20);
	}
	
	public void setDisable2(boolean value) {
		super.setDisable(value);
		this.getStyleClass().remove(!value ? "btn-disabled": "btn");
		this.getStyleClass().add(value ? "btn-disabled": "btn");
	}

}
