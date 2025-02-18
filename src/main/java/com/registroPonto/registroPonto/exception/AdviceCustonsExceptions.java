package com.registroPonto.registroPonto.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class AdviceCustonsExceptions  {

    StandardError err =new StandardError();

    @ExceptionHandler(EmailNotFound.class)
    public ResponseEntity<StandardError> emailNotFound(EmailNotFound exception, HttpServletRequest request){
     err.setInstante(Instant.now());
     err.setMesage("Email not found");
     err.setStatus(HttpStatus.NOT_FOUND.value());
     err.setPath( request.getRequestURI());
     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
    @ExceptionHandler(IdNotFound.class)
    public ResponseEntity<StandardError> idNotfound(IdNotFound exception, HttpServletRequest request){
        err.setInstante(Instant.now());
        err.setMesage("Id not found");
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setPath( request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
    @ExceptionHandler(TimeCheckException.class)
    public ResponseEntity<StandardError> idNotfound(TimeCheckException exception, HttpServletRequest request){
        err.setInstante(Instant.now());
        err.setMesage("CheckIn and CheckOut cannot be null");
        err.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        err.setPath( request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(err);
    }
}
