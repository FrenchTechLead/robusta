package org.javascool.exceptions;

public class CompilationException extends Exception {
	private static final long serialVersionUID = -6979729747044125636L;
	
	public CompilationException(){
		super();
	}
	
	public CompilationException(String message){
		super(message);
	}

}
