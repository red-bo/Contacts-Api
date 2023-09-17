package com.red.bo.web;

import com.red.bo.core.exception.ContactNotFoundException;
import com.red.bo.core.exception.DeleteContactException;
import com.red.bo.core.exception.UpdateContactException;
import com.red.bo.core.exception.generic.ApiError;
import com.red.bo.core.exception.generic.CustomHandlerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class CustomHandlerExceptionTest {

    @InjectMocks
    private CustomHandlerException customHandlerException;

    @Mock
    private WebRequest webRequest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHandleDeleteContactException() {
        DeleteContactException ex = new DeleteContactException("Delete error message");

        ResponseEntity<ApiError> response = customHandlerException.handleContactNotFoundException(ex, webRequest);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Contact could not be deleted", response.getBody().getMessage());
        assertEquals("Delete error message", response.getBody().getError());
    }

    @Test
    public void testHandleUpdateContactException() {
        UpdateContactException ex = new UpdateContactException("Update error message");

        ResponseEntity<ApiError> response = customHandlerException.handleContactNotFoundException(ex, webRequest);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Contact could not be updated", response.getBody().getMessage());
        assertEquals("Update error message", response.getBody().getError());
    }

    @Test
    public void testHandleContactNotFoundException() {
        ContactNotFoundException ex = new ContactNotFoundException("Not found error message");

        ResponseEntity<ApiError> response = customHandlerException.handleContactNotFoundException(ex, webRequest);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Contact not found", response.getBody().getMessage());
        assertEquals("Not found error message", response.getBody().getError());
    }
}
