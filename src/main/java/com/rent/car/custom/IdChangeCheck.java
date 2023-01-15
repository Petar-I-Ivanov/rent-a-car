package com.rent.car.custom;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class IdChangeCheck {

	private IdChangeCheck() {}
	
	public static BindingResult isChanged(int idValue, int idExpected, BindingResult bindingResult) {
		
		if (idValue != idExpected) {
			bindingResult.addError(new FieldError(bindingResult.getObjectName(), "id", "Don't change id's value."));
		}
		
		return bindingResult;
	}
}
