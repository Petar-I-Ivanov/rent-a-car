package com.rent.car.custom.validator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator<T> {

	public boolean isValueUniqueForItsField(List<T> list, String value, String fieldName) {
		
		Class<?> c = list.get(0).getClass();
		
		try {
			
			Method method = c.getDeclaredMethod("get" + fieldName, null);
				
			for (T item : list) {
				if (method.invoke(item, null).equals(value)) {
					return false;
				}
			}
			
		} catch (NoSuchMethodException | SecurityException |
				IllegalAccessException | IllegalArgumentException |
				InvocationTargetException e) {
			return false;
		}
		
		return true;
	}
}
