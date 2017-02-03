package br.com.spring.boot.thymeleaf.spring.security.core.enums;

public enum RoleEnum {

	ROLE_ADMIN("ADMIN"), ROLE_USER("USER");
	
	private String value;
	
	private RoleEnum(String value){
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
}
