package br.com.spring.boot.thymeleaf.spring.security.web.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ValidatePattern {

	public static boolean validate(final String regex, final String value){
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}
	
}
