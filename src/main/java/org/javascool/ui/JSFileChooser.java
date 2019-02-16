package org.javascool.ui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javafx.stage.FileChooser;
import lombok.Getter;

import org.javascool.models.FileModel;

public class JSFileChooser {

	private static File workingDirectory;
	private static FileChooser fs;
	@Getter
	public static FileModel fm = new FileModel();

	public static FileModel getFile() {

		workingDirectory = new File(System.getProperty("user.dir") + "/..");

		fs = new FileChooser();
		fs.setTitle("Selectionner le fichier .jvs");
		fs.setInitialDirectory(workingDirectory);
		fs.getExtensionFilters().add(new FileChooser.ExtensionFilter(".jvs file", "*.jvs"));

		File f = fs.showOpenDialog(null);
		if (null != f) {
			byte[] bytes;
			try {
				bytes = Files.readAllBytes(f.toPath());
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			
			fm.setFileContent(new String(bytes));
			fm.setFileName(f.getName());
			return fm;
		} else {
			return null;
		}
	}

}
