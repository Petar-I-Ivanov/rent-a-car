package com.rent.car.custom.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

import javax.validation.Constraint;
import javax.validation.ConstraintTarget;
import javax.validation.Payload;

// TODO: Make it automatic annotation

@Documented
//@Constraint(validatedBy = UniqueValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Unique {
	String message() default "There is another field record with this value.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
//    List<Object> listToCheck() default null;
//    String fieldName() default null;
}
