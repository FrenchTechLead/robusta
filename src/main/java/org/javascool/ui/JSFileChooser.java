package org.javascool.ui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javafx.stage.FileChooser;

public class JSFileChooser {

	private static File workingDirectory;
	private static FileChooser fs;
	public static File selectedFile;

	public static File getFile() {

		workingDirectory = new File(System.getProperty("user.dir") + "/..");

		fs = new FileChooser();
		fs.setTitle("Selectionner le fichier .jvs");
		fs.setInitialDirectory(workingDirectory);
		fs.getExtensionFilters().add(new FileChooser.ExtensionFilter(".jvs file", "*.jvs"));
		selectedFile = fs.showOpenDialog(null);
		return selectedFile;

	}

	public static String getFileContent() {
		if (null != selectedFile) {
			byte[] bytes;
			try {
				bytes = Files.readAllBytes(selectedFile.toPath());
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}

			return new String(bytes);
		} else {
			return null;
		}

	}
}
