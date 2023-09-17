package com.red.bo.web.skills.validation;

import com.red.bo.core.skills.SkillLevel;
import com.red.bo.web.skill.mapper.RequestedSkill;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RequestedSkillValidatorTest {

    private RequestedSkillValidator validator;
    private ConstraintValidatorContext context;

    @BeforeEach
    public void setUp() {
        validator = new RequestedSkillValidator();
        context = null;
    }

    @Test
    public void testValidSkill() {
        var skill = RequestedSkill.builder().name("Java").level(SkillLevel.EXPERT).build();
        boolean isValid = validator.isValid(skill, context);

        assertTrue(isValid, "Valid skill should return true");
    }

    @Test
    public void testInvalidSkill_NullObject() {
        RequestedSkill skill = null;
        boolean isValid = validator.isValid(skill, context);

        assertFalse(isValid, "Null skill should return false");
    }

    @Test
    public void testInvalidSkill_NullName() {
        var skill = RequestedSkill.builder().name(null).level(SkillLevel.EXPERT).build();
        boolean isValid = validator.isValid(skill, context);

        assertFalse(isValid, "Skill with null name should return false");
    }

    @Test
    public void testInvalidSkill_EmptyName() {
        var skill = RequestedSkill.builder().name("").level(SkillLevel.EXPERT).build();
        boolean isValid = validator.isValid(skill, context);

        assertFalse(isValid, "Skill with empty name should return false");
    }

    @Test
    public void testInvalidSkill_NullLevel() {
        var skill = RequestedSkill.builder().name("Java").level(null).build();
        boolean isValid = validator.isValid(skill, context);

        assertFalse(isValid, "Skill with null level should return false");
    }
}