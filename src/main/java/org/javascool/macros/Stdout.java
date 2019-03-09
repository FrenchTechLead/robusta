package org.javascool.macros;

import org.javascool.ui.Console;

import javafx.application.Platform;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Stdout {


	public static void print(Object o) {
		addJob(() -> {
			log.debug("print : " + o.toString());
			Console.getOutput().appendText(o.toString());
		});

	}

	public static void println(Object o) {
		addJob(() -> {
			log.debug("println : " + o.toString());
			Console.getOutput().appendText(o.toString() + "\n");
		});

	}

	public static void clear() {
		addJob(() -> {
			log.debug("clear");
			Console.getOutput().setText("");
		});

	}

	private static void addJob(Runnable t) {
		Platform.runLater(t);
	}
}
