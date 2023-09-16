package com.red.bo.web.contacts.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RequestedContactValidator.class)
public @interface ValidContact {
    String message() default "Invalid contact";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}