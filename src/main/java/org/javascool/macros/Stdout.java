package org.javascool.macros;

import javax.swing.SwingUtilities;

import org.javascool.ui.Console;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Stdout {

	public static void print(Object o) {
		SwingUtilities.invokeLater(() -> {
			log.debug("print : " + o.toString());
			Console.getOutput().append(o.toString());
		});
	}

	public static void println(Object o) {
		SwingUtilities.invokeLater(() -> {
			log.debug("println : " + o.toString());
			Console.getOutput().append(o.toString() + "\n");
		});

	}

	public static void clear() {
		SwingUtilities.invokeLater(() -> {
			log.debug("clear");
			Console.getOutput().setText("");
		});

	}
}
