package org.robusta.macros;

import javax.swing.JOptionPane;

public class Stdin {

	static final String DEFAULT_READER_TEXT = "Please enter a (an) ";
	
	private static String getResult(String text) {
		return JOptionPane.showInputDialog(Console.getInstance(), text);
	}

	/* ----- String Readers ----- */
	public static String readString(String text) {
		return getResult(text);
	}

	public static String readString() {
		return getResult(DEFAULT_READER_TEXT + "string");
	}

	/* ----- Double Readers ----- */
	public static Double readDouble(String text) {
		return new Double(getResult(text));
	}

	public static Double readDouble() {
		return new Double(getResult(DEFAULT_READER_TEXT + "decimal value (Double)"));
	}

	/* ----- Integer Readers ----- */
	public static Integer readInteger(String text) {
		return new Integer(getResult(text));
	}
	
	public static Integer readInt(String text) {
		return readInteger(text);
	}

	public static Integer readInteger() {
		return new Integer(getResult(DEFAULT_READER_TEXT + "(Integer)"));
	}
	
	public static Integer readInt() {
		return readInteger();
	}

	/* ----- Long Readers ----- */
	public static Long readLong(String text) {
		return new Long(getResult(text));
	}

	public static Long readLong() {
		return new Long(getResult(DEFAULT_READER_TEXT + "decimal value  (Long)"));
	}

	/* ----- Float Readers ----- */
	public static Float readFloat(String text) {
		return new Float(getResult(text));
	}

	public static Float readFloat() {
		return new Float(getResult(DEFAULT_READER_TEXT + "decimal value  (Float)"));
	}

	/* ----- Boolean Readers ----- */
	public static Boolean readBoolean(String text) {
		return new Boolean(getResult(text));
	}

	public static Boolean readBoolean() {
		return new Boolean(getResult(DEFAULT_READER_TEXT + "boolean value (true or false)"));
	}

	/* ----- Char Readers ----- */
	public static char readChar(String text) {
		String x = getResult(text);
		return x.length() > 0 ? x.charAt(0) : '\0';
	}
	
	public static char readCharacter(String text) {
		return readChar(text);
	}

	public static char readChar() {
		String x = getResult(DEFAULT_READER_TEXT + "character (one letter)");
		return x.length() > 0 ? x.charAt(0) : '\0';
	}
	
	public static char readCharacter() {
		return readChar();
	}

}
