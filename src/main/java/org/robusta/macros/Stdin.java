package org.robusta.macros;

import javax.swing.JOptionPane;

public class Stdin {

	static final String DEFAULT_READER_TEXT = "Veuillez entrer un(e) ";

	/* ----- String Readers ----- */
	public static String readString(String text) {
		return getResult(text);
	}

	public static String readString() {
		return getResult(DEFAULT_READER_TEXT + "chaine de caractères");
	}

	/* ----- Double Readers ----- */
	public static Double readDouble(String text) {
		return new Double(getResult(text));
	}

	public static Double readDouble() {
		return new Double(getResult(DEFAULT_READER_TEXT + "nombre décimal (Double)"));
	}

	/* ----- Integer Readers ----- */
	public static Integer readInteger(String text) {
		return new Integer(getResult(text));
	}

	public static Integer readInteger() {
		return new Integer(getResult(DEFAULT_READER_TEXT + "nombre entier (Integer)"));
	}

	/* ----- Long Readers ----- */
	public static Long readLong(String text) {
		return new Long(getResult(text));
	}

	public static Long readLong() {
		return new Long(getResult(DEFAULT_READER_TEXT + "nombre décimal (Long)"));
	}

	/* ----- Float Readers ----- */
	public static Float readFloat(String text) {
		return new Float(getResult(text));
	}

	public static Float readFloat() {
		return new Float(getResult(DEFAULT_READER_TEXT + "nombre décimal (Float)"));
	}

	/* ----- Boolean Readers ----- */
	public static Boolean readBoolean(String text) {
		return new Boolean(getResult(text));
	}

	public static Boolean readBoolean() {
		return new Boolean(getResult(DEFAULT_READER_TEXT + "valeur booléenne (true ou false)"));
	}

	private static String getResult(String text) {
		return JOptionPane.showInputDialog(Console.getInstance(), text);
	}
}
