package org.javascool;

import java.io.File;

import org.javascool.compiler.JarUtils;

public class Main2 {

	public static void main(String[] args) {
		JarUtils.create(new File(args[0]));

	}

}
