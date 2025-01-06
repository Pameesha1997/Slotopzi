package com.sliit.slotopzi.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.security.authentication.InternalAuthenticationServiceException;


@RestControllerAdvice
public class Errorhandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> handleInternalErrors(Exception e) {
        System.out.println(e);
        String error= e.getMessage();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


//    @ExceptionHandler(InternalAuthenticationServiceException.class)
//    public ResponseEntity<String> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException e) {
//        ResponseEntity<String> response = new ResponseEntity<String>("Incorrect Username or Password", HttpStatus.INTERNAL_SERVER_ERROR);
//        return response;
//    }
}
