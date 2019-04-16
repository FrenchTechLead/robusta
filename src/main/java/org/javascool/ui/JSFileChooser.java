package org.javascool.ui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class JSFileChooser {

	private static File workingDirectory;
	private static JFileChooser fs;
	public static File selectedFile;

	public static File getFile() {

		workingDirectory = new File(System.getProperty("user.dir") + "/..");

		fs = new JFileChooser(workingDirectory);
		fs.setDialogTitle("Selectionner le fichier .jvs");
		fs.setFileFilter(new FileNameExtensionFilter("*.jvs", "jvs"));
		int x =  fs.showOpenDialog(null);
		if(x==JFileChooser.APPROVE_OPTION){
			selectedFile = fs.getSelectedFile();
		} else {
			selectedFile = null;
		}
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
