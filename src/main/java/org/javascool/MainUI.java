package org.javascool;

import org.javascool.ui.CompileRow;
import org.javascool.ui.Console;
import org.javascool.ui.FileSelectionRow;
import org.javascool.ui.RunRow;

import javafx.application.Application;

import javafx.scene.Scene;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainUI extends Application {
	
	private static final String CSS_PATH = "styles.css";

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		StackPane root = new StackPane();
		log.debug("App loading ...");
		primaryStage.setTitle("Javascool light");
		
		Scene scene = new Scene(root, 600, 600);
		scene.getStylesheets().add(getClass().getResource(CSS_PATH).toExternalForm());
		
		VBox vbox =  new VBox();
		
		vbox.getChildren().addAll(
				FileSelectionRow.getComponent(scene),
				CompileRow.getComponent(scene),
				RunRow.getComponent(scene),
				Console.getComponent(scene)
				);
		
		root.getChildren().add(vbox);

		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.show();
	}
}