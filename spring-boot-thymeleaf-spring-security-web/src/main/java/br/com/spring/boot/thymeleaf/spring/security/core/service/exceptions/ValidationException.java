package br.com.spring.boot.thymeleaf.spring.security.core.service.exceptions;

public class ValidationException extends Exception {
	
	private static final long serialVersionUID = 8245097855746988770L;
	private String message;
	private String field;
	
	public ValidationException(String field, String message){
		super(message);
		this.field = field;
		this.message = message;
	}
	
	public String getMessage(){
		return message;
	}
	
	public String getField(){
		return field;
	}
	
}
