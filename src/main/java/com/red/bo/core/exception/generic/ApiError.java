package com.red.bo.core.exception.generic;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;

import java.util.Locale;

@AllArgsConstructor
@Data
public class ApiError implements ErrorResponse {

    private HttpStatus status;
    private String message;
    private String error;
    private String path;
    @Override
    public HttpStatusCode getStatusCode() {
        return status;
    }

    @Override
    public HttpHeaders getHeaders() {
        return ErrorResponse.super.getHeaders();
    }

    @Override
    public ProblemDetail getBody() {
        return null;
    }

    @Override
    public String getDetailMessageCode() {
        return ErrorResponse.super.getDetailMessageCode();
    }

    @Override
    public Object[] getDetailMessageArguments() {
        return ErrorResponse.super.getDetailMessageArguments();
    }

    @Override
    public Object[] getDetailMessageArguments(MessageSource messageSource, Locale locale) {
        return ErrorResponse.super.getDetailMessageArguments(messageSource, locale);
    }

    @Override
    public String getTitleMessageCode() {
        return ErrorResponse.super.getTitleMessageCode();
    }

    @Override
    public ProblemDetail updateAndGetBody(MessageSource messageSource, Locale locale) {
        return ErrorResponse.super.updateAndGetBody(messageSource, locale);
    }
}
