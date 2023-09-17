package com.red.bo.web.contacts.validation;

import com.red.bo.web.contact.mapper.RequestedContact;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RequestedContactValidatorTest {

    private RequestedContactValidator validator;
    private ConstraintValidatorContext context;

    @BeforeEach
    public void setUp() {
        validator = new RequestedContactValidator();
        context = null;
    }

    @Test
    public void testValidContact() {
        var contact = RequestedContact.builder().firstname("John").lastname("Doe").fullname("John Doe").email("john.doe@email.com").build();
        boolean isValid = validator.isValid(contact, context);

        assertTrue(isValid, "Valid contact should return true");
    }

    @Test
    public void testInvalidContact_NullObject() {
        RequestedContact contact = null;
        boolean isValid = validator.isValid(contact, context);

        assertFalse(isValid, "Null contact should return false");
    }

    @Test
    public void testInvalidContact_EmptyName() {
        var contact = RequestedContact.builder().firstname("").lastname("Doe").fullname("John Doe").email("john.doe@email.com").build();
        boolean isValid = validator.isValid(contact, context);

        assertFalse(isValid, "Contact with empty first name should return false");
    }

    @Test
    public void testInvalidContact_NullEmail() {
        var contact = RequestedContact.builder().firstname("John").lastname("Doe").fullname("John Doe").email(null).build();
        boolean isValid = validator.isValid(contact, context);

        assertFalse(isValid, "Contact with null email should return false");
    }

    @Test
    public void testInvalidContact_InvalidEmail() {
        var contact = RequestedContact.builder().firstname("John").lastname("Doe").fullname("John Doe").email("invalid-email").build();
        boolean isValid = validator.isValid(contact, context);

        assertFalse(isValid, "Contact with invalid email should return false");
    }

}
