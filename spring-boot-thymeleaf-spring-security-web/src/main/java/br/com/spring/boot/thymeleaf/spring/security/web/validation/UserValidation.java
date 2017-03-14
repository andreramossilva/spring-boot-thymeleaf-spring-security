package br.com.spring.boot.thymeleaf.spring.security.web.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

import br.com.spring.boot.thymeleaf.spring.security.core.entities.User;

public class UserValidation extends BasicValidator implements Validator {
	
	private User user;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		this.user = (User) target;
		this.errors = errors;
		
		name();
		email(user.getEmail());
		password();
		
	}
	
	private void name() {
		isValid(user.getName(), UserFiledsName.NAME, FieldsLengthEnum._256);
	}
	
	private void password(){
		if(user.getId() == null || !StringUtils.isEmptyOrWhitespace(user.getPassword())){
			isValid(user.getPassword(), UserFiledsName.PASSWORD, FieldsLengthEnum._60);
			isValid(user.getConfirmPassword(), UserFiledsName.CONFIRM_PASSWORD, FieldsLengthEnum._60);
			
			if(user.getPassword() != null && user.getConfirmPassword() != null && !user.getPassword().equals(user.getConfirmPassword())){
				errors.rejectValue(UserFiledsName.CONFIRM_PASSWORD, "field.invalid.user.password.confirmation");
			}
		}
		
	}
	
}
