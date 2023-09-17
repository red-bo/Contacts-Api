package com.red.bo.web.contacts.validation;

import com.red.bo.web.contact.mapper.RequestedContact;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RequestedContactValidator implements ConstraintValidator<ValidContact, RequestedContact> {
    @Override
    public boolean isValid(RequestedContact contact, ConstraintValidatorContext context) {
        if (contact == null) {
            return false;
        }

        if (isEmpty(contact.getFirstname()) || isEmpty(contact.getLastname()) || isEmpty(contact.getFullname())) {
            return false;
        }

        if (contact.getEmail() == null || !isValidEmail(contact.getEmail())) {
            return false;
        }

        return true;
    }

    private boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}