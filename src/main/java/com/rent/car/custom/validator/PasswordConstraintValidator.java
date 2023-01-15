package com.rent.car.custom.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
	
	@Override
    public void initialize(ValidPassword password) {}

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		
		List<String> errors = new ArrayList<String>();
		
		if (!isLengthValid(password)) {
			errors.add("Password length must be between 8 and 30.");
		}
		
		if (!isContainsFiveLetters(password)) {
			errors.add("Password must contains atleast five letters.");
		}
		
		if (!isContainsDigit(password)) {
			errors.add("Password must contains atleast one digit.");
		}
		
		if (!isContainsUpperCase(password)) {
			errors.add("Password must contains atleast one upper case.");
		}
		
		if (!isContainsSpecialSymbols(password)) {
			errors.add("Password must contains atleast one special symbol (_-%@$!).");
		}
		
		if (!areOnlyAllowedChars(password)) {
			errors.add("Only allowed symbols are letters, digits, _-%@$!");
		}
		
		if (errors.size() != 0) {
			
			context.disableDefaultConstraintViolation();
	        context.buildConstraintViolationWithTemplate(
	        			String.join(" ", errors))
	        		.addConstraintViolation();
			
			return false;
		}
		
		return true;
	}
	
	private boolean isLengthValid(String password) {
		return password.length() >= 8 && password.length() <= 30;
	}
	
	private boolean isContainsFiveLetters(String password) {
		
		int count = 0;
		
		for (Character character : password.toCharArray()) {
			if (character >= 'a' && character < 'z' ||
				character >= 'A' && character < 'Z') {
				count++;
			}
		}
		
		return count >= 5;
	}
	
	private boolean isContainsUpperCase(String password) {
		
		for (Character character : password.toCharArray()) {
			if (character >= 'A' && character < 'Z') {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean isContainsDigit(String password) {
		
		for (Character character : password.toCharArray()) {
			if (character >= '0' && character < '9') {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean isContainsSpecialSymbols(String password) {
		
		for (Character character : password.toCharArray()) {
			if (character == '_' || character == '-' || character == '%' ||
				character == '@' || character == '$' || character == '!') {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean areOnlyAllowedChars(String password) {
		return password.matches("[a-zA-Z0-9\\_\\-\\%\\@\\$\\!]");
	}
}
