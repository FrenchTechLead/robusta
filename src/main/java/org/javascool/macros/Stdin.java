package org.javascool.macros;

import javafx.scene.control.TextInputDialog;

public class Stdin {
	
	static private TextInputDialog dialog = new TextInputDialog();

  
	public static String readString(String text) {
		dialog = new TextInputDialog();
		dialog.setHeaderText(text);
		dialog.showAndWait();
		return dialog.getResult();
	}
	
	public static Double readDouble(String text) {
		return new Double(readString(text));
	}
}

