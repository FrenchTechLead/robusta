package org.javascool.ui;

import javax.swing.JButton;

public class CustomButton extends JButton {
	
	private static final long serialVersionUID = -8001873888481812609L;

	CustomButton(String Btntext) {
		super();
		this.setText(Btntext);
	    this.setSize(100, 20);
	}

}
