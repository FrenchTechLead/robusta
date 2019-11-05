package org.robusta.macros;

import java.util.Scanner;
import java.io.OutputStreamWriter;

/*
 * This class has been copied from https://github.com/svn2github/javascool/blob/926363015a7e6d089b747152afb4d81586cb9396/javascool_V4/work/src/org/javascool/macros/ManuelIsn.java
 * Might be necessary for course: Programmation 2 - Cours  7
 * */
public class IO {

	private static final Object readMonitor = new Object();
	private static final java.util.regex.Pattern EOLN = java.util.regex.Pattern.compile(".*?(?:\r(?!\n)|\n|\r\n)");
	private static final java.util.regex.Pattern ALL = java.util.regex.Pattern.compile(".*+");

	public static boolean stringEqual(String s1, String s2) {
		return s1.equals(s2);
	}

	public static boolean stringAlph(String s1, String s2) {
		return s1.compareTo(s2) <= 0;
	}

	public static String stringNth(String s, int n) {
		return s.substring(n, n + 1);
	}

	public static int stringLength(String s) {
		return s.length();
	}

	public static String asciiString(int n) {
		byte[] b;
		b = new byte[1];
		b[0] = (byte) n;
		return new String(b);
	}

	public static int stringCode(String s) {
		return (int) (s.charAt(0));
	}

	private static Scanner scanner(java.io.Reader in) {
		Scanner scanner = new Scanner(in);
		scanner.useLocale(java.util.Locale.US);
		return scanner;
	}

	public static Scanner openIn(String name) {
		try {
			java.io.FileInputStream fis = new java.io.FileInputStream(name);
			Scanner scanner = scanner(new java.io.InputStreamReader(fis));
			return scanner;
		} catch (java.io.FileNotFoundException e) {
			return scanner(new java.io.InputStreamReader(System.in));
		}
	}

	public static void closeIn(Scanner s) {
		s.close();
	}

	public static int readIntFromFile(Scanner s) {
		synchronized (readMonitor) {
			return s.nextInt();
		}
	}

	public static double readDoubleFromChar(Scanner s) {
		synchronized (readMonitor) {
			return s.nextDouble();
		}
	}

	private static final java.util.regex.Pattern DOT = java.util.regex.Pattern.compile(".",
			java.util.regex.Pattern.DOTALL);

	public static String readCharacterFromFile(Scanner s) {
		synchronized (readMonitor) {
			return String.valueOf(s.findWithinHorizon(DOT, 1).charAt(0));
		}
	}

	public static String readStringFromFile(Scanner s) {
		String r = s.findWithinHorizon(EOLN, 0);
		if (r == null)
			return s.findWithinHorizon(ALL, 0);
		if (r.length() == 1)
			return "";
		int pos = r.charAt(r.length() - 2) == '\r' ? r.length() - 2 : r.length() - 1;
		return r.substring(0, pos);
	}

	public static OutputStreamWriter openOut(String name) {
		try {
			java.io.FileOutputStream fos = new java.io.FileOutputStream(name);
			java.io.OutputStreamWriter out = new java.io.OutputStreamWriter(fos, "UTF-8");
			return out;
		} catch (java.io.FileNotFoundException e) {
			return new java.io.OutputStreamWriter(System.out);
		} catch (java.io.UnsupportedEncodingException e) {
			return new java.io.OutputStreamWriter(System.out);
		}
	}

	public static void closeOut(OutputStreamWriter s) {
		try {
			s.close();
		} catch (java.io.IOException e) {
		}
	}

	public static void printlnToFile(OutputStreamWriter s) {
		try {
			s.write(System.getProperty("line.separator"));
		} catch (java.io.IOException e) {
		}
	}

	public static void printToFile(OutputStreamWriter s, String n) {
		try {
			s.write(n);
		} catch (java.io.IOException e) {
		}
	}

	public static void printlnToFile(OutputStreamWriter s, String n) {
		printToFile(s, n);
		printlnToFile(s);
	}

	public static void printToFile(OutputStreamWriter s, int n) {
		try {
			s.write(String.valueOf(n));
		} catch (java.io.IOException e) {
		}
	}

	public static void printlnToFile(OutputStreamWriter s, int n) {
		printToFile(s, n);
		printlnToFile(s);
	}

	public static void printToFile(OutputStreamWriter s, double n) {
		try {
			s.write(String.valueOf(n));
		} catch (java.io.IOException e) {
		}
	}

	public static void printlnToFile(OutputStreamWriter s, double n) {
		printToFile(s, n);
		printlnToFile(s);
	}
}
