package com.red.bo.web.skills.validation;


import com.red.bo.web.skill.mapper.RequestedSkill;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class RequestedSkillValidator implements ConstraintValidator<ValidSkill, RequestedSkill> {

    @Override
    public void initialize(ValidSkill constraintAnnotation) {
    }

    @Override
    public boolean isValid(RequestedSkill skill, ConstraintValidatorContext context) {
        if (skill == null) {
            return false;
        }

        if (skill.getName() == null || skill.getName().trim().isEmpty()) {
            return false;
        }

        if (skill.getLevel() == null) {
            return false;
        }

        return true;
    }
}
