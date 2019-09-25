package org.javascool.compiler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

public class JarUtils {
	private static final File parentDirectory = new File(System.getProperty("user.dir"));

	public static void create(File jvsFile) {
		Manifest mf = getManifest("C");
		String[] jarEntries = { "a.jar", "C.class" };
		
		String fileName = jvsFile.getName();
		int index = fileName.lastIndexOf('.');
		jarCreate(jvsFile.getParent() + File.separator + fileName.substring(0, index)+ ".jar", mf, parentDirectory.getAbsolutePath(), jarEntries);
		System.out.println("Compilation réussie");
	}

	/**
	 * Generates manifest file.
	 * 
	 * @param mainClass The main class that should be executed in jar.
	 * @return Manifest Object.
	 */
	@SuppressWarnings("deprecation")
	private static Manifest getManifest(String mainClass) {
		Manifest manifest = new Manifest();
		Attributes main = manifest.getMainAttributes();
		main.putValue("Manifest-Version", "1.0");
		main.putValue("Created-By", "1.0 (javascool-light)");
		main.putValue("Created-Time", new Date(System.currentTimeMillis()).toGMTString());
		main.putValue("Main-Class", mainClass);
		main.putValue("Class-Path", parentDirectory.getAbsolutePath() + File.separator +"a.jar");
		return manifest;
	}

	/**
	 * Creates a jar file from the content of a folder.
	 *
	 * @param jarFile    Path of the jar file (it gets deleted if existing) .
	 * @param manifest   Manifest object of the jar.
	 * @param srcDir     The folder containing source to be added.
	 * @param jarEntries Files inside src dir that should be added, if null all
	 *                   files gets added.
	 */
	public static void jarCreate(String jarFile, Manifest manifest, String srcDir, String[] jarEntries) {
		try {
			File parent = new File(jarFile).getParentFile();
			if (parent != null) {
				parent.mkdirs();
			}
			new File(jarFile).delete();
			srcDir = new File(srcDir).getCanonicalPath();
			JarOutputStream target = new JarOutputStream(new FileOutputStream(jarFile), manifest);
			JarUtils.copyFileToJar(new File(srcDir), target, new File(srcDir), jarEntries);
			target.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	private static void copyFileToJar(File source, JarOutputStream target, File root, String[] jarEntries)
			throws IOException {
		if (jarEntries != null) {
			boolean skip = true;
			for (String jarEntry : jarEntries) {
				String entry = root.toString() + File.separator + jarEntry;
				skip &= !(entry.startsWith(source.toString()) | source.toString().startsWith(entry));
			}
			if (skip) {
				return;
			}
		}
		try {
			if (source.isDirectory()) {
				String name = source.getPath().replace(root.getAbsolutePath() + File.separator, "")
						.replace(File.separator, "/");
				if (!name.isEmpty() && (!source.equals(root))) {
					if (!name.endsWith("/")) {
						name += "/";
					}
					JarEntry entry = new JarEntry(name);
					entry.setTime(source.lastModified());
					target.putNextEntry(entry);
					target.closeEntry();
				}
				for (File nestedFile : source.listFiles()) {
					JarUtils.copyFileToJar(nestedFile, target, root, jarEntries);
				}
			} else {
				JarEntry entry = new JarEntry(source.getPath().replace(root.getAbsolutePath() + File.separator, "")
						.replace(File.separator, "/"));
				entry.setTime(source.lastModified());
				target.putNextEntry(entry);
				JarUtils.copyStream(new BufferedInputStream(new FileInputStream(source)), target);
			}
		} catch (Throwable e) {
			e.printStackTrace(System.out);
			throw new IllegalStateException(e);
		}
	}

	private static void copyStream(InputStream in, OutputStream out, DownloadListener listener, int size)
			throws IOException {
		InputStream i = in instanceof JarInputStream ? in : new BufferedInputStream(in, 2048);
		OutputStream o = out instanceof JarOutputStream ? out : new BufferedOutputStream(out, 2048);
		byte data[] = new byte[2048];
		for (int c, l = 0; (c = i.read(data, 0, 2048)) != -1;) {
			o.write(data, 0, c);
			if (listener != null) {
				listener.progressPerformed(l += c, size);
			}
		}
		if (o instanceof JarOutputStream) {
			((JarOutputStream) o).closeEntry();
		} else {
			o.close();
		}
		if (i instanceof JarInputStream) {
			((JarInputStream) i).closeEntry();
		} else {
			i.close();
		}
	}

	private static void copyStream(InputStream in, OutputStream out) throws IOException {
		JarUtils.copyStream(in, out, null, 0);
	}

	public static interface DownloadListener {

		public void progressPerformed(int currentSize, int totalSize);
	}
}
