package br.com.spring.boot.thymeleaf.spring.security.web.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.spring.boot.thymeleaf.spring.security.core.entities.User;

public class UserValidation implements Validator {
	
	private User user;
	private Errors errors;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		this.user = (User) target;
		this.errors = errors;
		
		name();
		email();
		cpf();
		password();
		
	}
	
	private void name() {
		isValid(user.getName(), FiledsName.NAME, FieldsLengthEnum._256);
	}
	
	private void email() {
		
		isValid(user.getEmail(), FiledsName.EMAIL, FieldsLengthEnum._256);
		
		boolean validate = ValidatePattern.validate(Patterns.EMAIL_PATTERN, user.getEmail());
		if(!validate){
			errors.rejectValue(FiledsName.EMAIL, "field.invalid.user.email");
		}
		
	}
	
	private void cpf(){
		
		isValid(user.getFederalId(), FiledsName.FEDERAL_ID, FieldsLengthEnum._14);
		
		CPFValidator validator = new CPFValidator();
		
		try{
			validator.assertValid(user.getFederalId());
		} catch (InvalidStateException e) { 
			errors.rejectValue(FiledsName.FEDERAL_ID, "field.invalid.user.federal.id");
		}
		
	}
	
	private void password(){
		isValid(user.getPassword(), FiledsName.PASSWORD, FieldsLengthEnum._60);
		isValid(user.getConfirmPassword(), FiledsName.CONFIRM_PASSWORD, FieldsLengthEnum._60);
		
		if(user.getPassword() != null && user.getConfirmPassword() != null && !user.getPassword().equals(user.getConfirmPassword())){
			errors.rejectValue(FiledsName.CONFIRM_PASSWORD, "field.invalid.user.password.confirmation");
		}
		
	}
	
	private void isValid(final String value, final String fieldName, final FieldsLengthEnum length){
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, fieldName, FieldsDescription.FIELD_REQUIRED);
		
		if(value != null){
			if(value.length() > length.getValue()){
				errors.rejectValue(fieldName, FieldsDescription.MAX_LENGTH, new String[]{length.getValue().toString()}, null);
			}
		}
		
	}

}
