package org.robusta.macros;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Console extends JFrame {

	private static Console CONSOLE_INSTANCE;
	private static volatile JTextArea output = new JTextArea();
	private static volatile JScrollPane scrollFrame;
	private static final long serialVersionUID = 6543132165763L;
	
	public static void main(String [] s) {
		new Console();
		Integer i = Stdin.readInteger();
	}
	
	private Console() {
		super("Robusta");

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
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
		scrollFrame = new JScrollPane (output, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(780, 600));
		p.setLayout(new BorderLayout());
		p.add(scrollFrame, BorderLayout.CENTER);
		return p;
	}
	
	public static synchronized JTextArea getOutput() {
		return output;
	}
	
	private void start() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setAlwaysOnTop(false);
		this.setSize(800, 600);
		getContentPane().add(Console.getConsole());
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
	}
		
	
}
