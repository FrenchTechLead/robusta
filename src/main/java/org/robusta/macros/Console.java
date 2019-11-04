package org.robusta.macros;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Console extends JFrame {

	private static Console CONSOLE_INSTANCE;
	private static volatile JTextArea output = new JTextArea();
	private static final long serialVersionUID = 6543132165763L;
	
	private Console() {
		super("Javascool light");
		WindowListener l = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		addWindowListener(l);
		this.start();
	}
	
	public static synchronized Console getInstance() {
		if(CONSOLE_INSTANCE == null) {
			return new Console();
		} else {
			return CONSOLE_INSTANCE;
		}
	}
	
	private static JPanel getConsole() {
		output.setEditable(false);
		output.setVisible(true);
		output.setBackground(Color.BLACK);
		output.setForeground(Color.ORANGE);
		output.setFont(new Font("monospaced", Font.PLAIN, 15));
		JScrollPane scroll = new JScrollPane (output, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(800, 600));
		p.setLayout(new BorderLayout());
		p.add(scroll, BorderLayout.CENTER);
		return p;
	}
	
	public static synchronized JTextArea getOutput() {
		return output;
	}
	
	private void start() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		JPanel secondPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		secondPanel.add(Console.getConsole());
		contentPane.add(secondPanel, BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);
	}
}