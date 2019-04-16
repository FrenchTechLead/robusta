package org.javascool.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Console {

	private static volatile JTextArea output = new JTextArea();
	
	public static JPanel getComponent() {
		output.setEditable(false);
		output.setVisible(true);
		output.setBackground(Color.BLACK);
		output.setForeground(Color.WHITE);
		JScrollPane scroll = new JScrollPane (output, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(790, 500));
		p.setLayout(new BorderLayout());
		p.add(scroll, BorderLayout.CENTER);
		p.add(getControlBtn(), BorderLayout.SOUTH);
		return p;
	}
	
	private static CustomButton getControlBtn() {
		CustomButton btnEffacer =  new CustomButton("Effacer");
		btnEffacer.addActionListener((e)-> output.setText(""));
		return btnEffacer;
	}
	
	public static synchronized JTextArea getOutput() {
		return output;
	}

}
