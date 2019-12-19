package org.robusta.compiler;

import java.io.File;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.JavaFormatterOptions;
import com.google.googlejavaformat.java.JavaFormatterOptions.Style;

public class JvsFormatter {
	
	
	public static void format(String javaFilePath) {
		File jvsFile = new File(javaFilePath);
		String jvsCode = JvsCompiler.readFile(jvsFile);
		String wrappedCode = "class Toto { " + jvsCode + "}";
		
		JavaFormatterOptions jfo = JavaFormatterOptions.builder().style(Style.AOSP).build();
		Formatter formatter = new Formatter(jfo);
		try {
			String formatted = "";
			String wrappedCodeFormatted = formatter.formatSource(wrappedCode);
			String lines[] = wrappedCodeFormatted.split("\\r?\\n");
			for(int i = 1 ; i < lines.length -1 ; i ++) {
				if(lines[i].length() >= 4) {
					formatted = formatted + lines[i].substring(4) + "\n";
				} else {
					formatted = formatted + lines[i] + "\n";
				}
			}
			FileUtils.write(jvsFile, formatted, Charset.defaultCharset());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
