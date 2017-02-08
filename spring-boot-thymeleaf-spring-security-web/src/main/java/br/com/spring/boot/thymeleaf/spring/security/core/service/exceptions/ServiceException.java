package br.com.spring.boot.thymeleaf.spring.security.core.service.exceptions;

public class ServiceException extends Exception {
	
	private static final long serialVersionUID = 2018244502425701753L;
	private String message;
	
	public ServiceException(){
		super();
	}
	
	public ServiceException(String message){
		super(message);
		this.message = message;
	}
	
	public ServiceException(Throwable cause) {
        super(cause);
    }
	
	public ServiceException(String message, Throwable cause){
		super(message, cause);
		this.message = message;
	}
	
	public String getMessage(){
		return message;
	}
	
}
