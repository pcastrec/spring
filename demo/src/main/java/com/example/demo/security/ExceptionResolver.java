package com.example.demo.security;

import java.nio.file.AccessDeniedException;
import java.security.SignatureException;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;

public class ExceptionResolver extends ResponseEntityExceptionHandler {

    private ProblemDetail createProblemDetail(int status, String message, String description) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(status), message);
        problemDetail.setProperty("description", description);
        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception exception) {

        return switch (exception) {
            case BadCredentialsException e -> createProblemDetail(401, e.getMessage(), "Username or password is incorrect");
            case AccountStatusException e -> createProblemDetail(403, e.getMessage(), "The account is locked");
            case AccessDeniedException e -> createProblemDetail(403, e.getMessage(), "You are not authorized to access this resource");
            case SignatureException e -> createProblemDetail(403, e.getMessage(), "The JWT signature is invalid");
            case ExpiredJwtException e -> createProblemDetail(403, e.getMessage(), "The JWT token has expired");
            default -> createProblemDetail(500, exception.getMessage(), "Unknonw internal server error");
        };
    }
}
