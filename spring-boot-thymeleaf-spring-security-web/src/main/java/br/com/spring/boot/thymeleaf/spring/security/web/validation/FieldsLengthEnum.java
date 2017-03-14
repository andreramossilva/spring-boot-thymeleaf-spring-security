package br.com.spring.boot.thymeleaf.spring.security.web.validation;

public enum FieldsLengthEnum {
	
	_256(256), _14(14), _60(60), _11(11), _128(128);
	
	private Integer value;
	
	private FieldsLengthEnum(Integer value){
		this.value = value;
	}
	
	public Integer getValue(){
		return value;
	}
	
}
