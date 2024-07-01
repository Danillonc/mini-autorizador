package com.mini.autorizador.application.exceptionhandler;

import com.mini.autorizador.domain.exception.CreditCardException;
import com.mini.autorizador.domain.exception.CreditCardInvalidFundsException;
import com.mini.autorizador.domain.exception.CreditCardInvalidPasswordException;
import com.mini.autorizador.domain.exception.CreditCardNotExistsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, "Parâmetros Inválidos", errors);
        return new ResponseEntity<Object>(apiError, headers, apiError.getStatus());
    }

    @ExceptionHandler(CreditCardException.class)
    public ResponseEntity<Object> handleCreditCardExistsException(CreditCardException ex){
        ApiError apiError =
                new ApiError(HttpStatus.UNPROCESSABLE_ENTITY, ex.getLocalizedMessage(), ex.getMessage());
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(CreditCardNotExistsException.class)
    public ResponseEntity<Object> handleCreditCardNotExistsException(CreditCardNotExistsException ex){
        ApiError apiError =
                new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), ex.getCause().getMessage());
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(CreditCardInvalidPasswordException.class)
    public ResponseEntity<Object> handleCreditCardInvalidPasswordException(CreditCardInvalidPasswordException ex){
        ApiError apiError =
                new ApiError(HttpStatus.UNPROCESSABLE_ENTITY, ex.getLocalizedMessage(), ex.getCause().getMessage());
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(CreditCardInvalidFundsException.class)
    public ResponseEntity<Object> handleCreditCardInvalidFundsException(CreditCardInvalidFundsException ex){
        ApiError apiError =
                new ApiError(HttpStatus.UNPROCESSABLE_ENTITY, ex.getLocalizedMessage(), ex.getCause().getMessage());
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }



}
