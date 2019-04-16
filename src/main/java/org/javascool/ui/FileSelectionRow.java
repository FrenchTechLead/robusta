package org.javascool.ui;

import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileSelectionRow {

	public static JLabel label1 = new JLabel("1");
	public static JLabel label2 = new JLabel("Aucun fichier.");

	public static JPanel getComponent() {

		CustomButton btn = new CustomButton( "Ouvrir");
		btn.addActionListener(event -> {
			Console.getOutput().setText("");
			File f = JSFileChooser.getFile();
			if (f != null) {
				String fileName = f.getName();
				label2.setText(fileName);
				log.info("Selected file : " + fileName);
				CompileRow.getCompileBtn().setEnabled(true);
				RunRow.getRunBtn().setEnabled(false);
			} else {
				label2.setText("Aucun fichier");
				log.info("Aucun fichier sélectionné");
				CompileRow.getCompileBtn().setEnabled(false);
				RunRow.getRunBtn().setEnabled(false);
			}

		});

		JPanel p = new JPanel();
		p.add(label1);
		p.add(btn);
		p.add(label2);
		p.setVisible(true);
		return p;
	}

}
