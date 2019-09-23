package org.javascool.compiler;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.jar.Pack200;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;

public class JarUtils {
	private static final File parentDirectory = new File(System.getProperty("user.dir"));

	public static void create(File jvsFile) {
		// Create Manifest File
		File manifestFile = new File(parentDirectory.getAbsolutePath() + "/META-INF/MANIFEST.MF");
		FileOutputStream os = null;
		FileOutputStream os2;
		JarOutputStream jostream;
		FileInputStream is;
		try {
			os = FileUtils.openOutputStream(manifestFile);
			getManifest("C").write(os);
			Pack200.Packer p = Pack200.newPacker();
			os2 = new FileOutputStream(new File(parentDirectory.getAbsolutePath() + "/out.jar"));
			jostream = new JarOutputStream(os2);
			IOFileFilter iof = new IOFileFilter() {

				@Override
				public boolean accept(File dir, String name) {
					return true;
				}

				@Override
				public boolean accept(File file) {
					String name = file.getName();
					List<String> blackList = Arrays.asList(new String[] { ".sh", ".bat", ".json", "D" });
					int i = name.lastIndexOf(".");
					if (i > 0) {
						String extension = name.substring(i);
						return !blackList.contains(extension);
					} else {
						return false;
					}

				}
			};
			FileUtils.iterateFilesAndDirs(parentDirectory, iof, iof).forEachRemaining((x) -> {
				try {
					if (x.isFile())
						jostream.write(Files.readAllBytes(x.toPath()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("deprecation")
	private static Manifest getManifest(String mainClass) {
		Manifest manifest = new Manifest();
		Attributes main = manifest.getMainAttributes();
		main.putValue("Manifest-Version", "1.0");
		main.putValue("Created-By", "1.0 (javascool-light)");
		main.putValue("Created-Time", new Date(System.currentTimeMillis()).toGMTString());
		main.putValue("Main-Class", mainClass);
		return manifest;
	}
}
