package org.javascool.macros;

import javax.swing.JOptionPane;

public class Stdin {
  
	public static String readString(String text) {
		return JOptionPane.showInputDialog(text);
	}
}

