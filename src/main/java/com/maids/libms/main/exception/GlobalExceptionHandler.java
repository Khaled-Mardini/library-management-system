package com.maids.libms.main.exception;


import com.maids.libms.main.ResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        return ResponseDto.exceptionResponse(HttpStatus.BAD_REQUEST, errors.toArray(String[]::new));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        return ResponseDto.exceptionResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
    }

    String filterMessage(String message) {
        if (message.contains("Duplicate entry")) {
            return "this entity already exists";
        }
        return message;
    }

    @ExceptionHandler(CommonExceptions.ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(CommonExceptions.ResourceNotFoundException ex) {
        return ResponseDto.exceptionResponse(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
    }

    @ExceptionHandler(CommonExceptions.BadRequestException.class)
    public ResponseEntity<Object> handleBadRequest(CommonExceptions.BadRequestException ex) {
        return ResponseDto.exceptionResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
    }

    @ExceptionHandler(CommonExceptions.UnauthorizedException.class)
    public ResponseEntity<Object> handleUnauthorized(CommonExceptions.UnauthorizedException ex) {
        return ResponseDto.exceptionResponse(HttpStatus.UNAUTHORIZED, ex.getLocalizedMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDenied(AccessDeniedException ex) {
        return ResponseDto.exceptionResponse(HttpStatus.UNAUTHORIZED, ex.getLocalizedMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthentication(AuthenticationException ex) {
        return ResponseDto.exceptionResponse(HttpStatus.FORBIDDEN, ex.getLocalizedMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentials(BadCredentialsException ex) {
        return ResponseDto.exceptionResponse(HttpStatus.FORBIDDEN, ex.getLocalizedMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(Exception ex) {
        String message = filterMessage(ex.getLocalizedMessage());
        return ResponseDto.exceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    @ExceptionHandler(CommonExceptions.ResourceAlreadyExistException.class)
    public ResponseEntity<Object> handleResourceAlreadyExist(CommonExceptions.ResourceAlreadyExistException ex) {
        return ResponseDto.exceptionResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
    }
}
