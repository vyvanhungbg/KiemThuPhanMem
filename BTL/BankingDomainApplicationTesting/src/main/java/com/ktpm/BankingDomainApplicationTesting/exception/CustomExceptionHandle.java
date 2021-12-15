package com.ktpm.BankingDomainApplicationTesting.exception;

import com.ktpm.BankingDomainApplicationTesting.entity.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandle {
    @ExceptionHandler(ErrorAccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseObject errorAccessDeniedException(ErrorAccessDeniedException exception) {
          return new ResponseObject(false,exception.getMessage(),null);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseObject errorAccessDenied(AccessDeniedException exception) {
        return new ResponseObject(false,exception.getMessage(),null);
    }


    @ExceptionHandler(ErrorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseObject errorNotFoundException(ErrorNotFoundException exception) {
        return new ResponseObject(false,exception.getMessage(),null);
    }

    @ExceptionHandler(ErrorExecutionFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseObject ErrorExecutionFailedException(ErrorExecutionFailedException exception) {
        return new ResponseObject(false,exception.getMessage(),null);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseObject ErrorUsernameNotFoundException(UsernameNotFoundException exception) {
        return new ResponseObject(false,"Unable to verify user",exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseObject handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseObject(false,"Error fields",errors);
    }
}
