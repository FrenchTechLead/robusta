package org.javascool.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.javascool.compiler.JavaRuntimeCompiler;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunRow {
	public static JLabel label1 = new JLabel("3");
	@Getter
	public static CustomButton runBtn;
	
	public static JPanel getComponent() {
		
		
		runBtn = new CustomButton("Exécuter");
		runBtn.setEnabled(false);
		runBtn.addActionListener( event -> {
			log.info("Running the code.");
			Console.getOutput().setText("");
			JavaRuntimeCompiler.doRun();
		});
	    
	    JPanel p = new JPanel();
		p.add(label1);
		p.add(runBtn);
		p.setVisible(true);
		return p;
	}
}
