package com.red.bo.web.skills.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RequestedSkillValidator.class)
public @interface ValidSkill {
    String message() default "Invalid skill";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
