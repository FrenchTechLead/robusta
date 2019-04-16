package org.javascool;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.javascool.ui.CompileRow;
import org.javascool.ui.Console;
import org.javascool.ui.FileSelectionRow;
import org.javascool.ui.RunRow;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainUI extends JFrame {

	private static final long serialVersionUID = 6543132165763L;

	MainUI() {
		super("Javascool light");
		WindowListener l = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		addWindowListener(l);
	}

	public static void main(String[] args) {
		MainUI ui = new MainUI();
		ui.start();
	}

	public void start() {
		log.debug("App loading ...");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		JPanel firstPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		firstPanel.add(FileSelectionRow.getComponent());
		firstPanel.add(CompileRow.getComponent());
		firstPanel.add(RunRow.getComponent());
		contentPane.add(firstPanel, BorderLayout.NORTH);
		
		JPanel secondPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		secondPanel.add(Console.getComponent());
		
		contentPane.add(secondPanel, BorderLayout.CENTER);
		
		this.setVisible(true);
	}
}