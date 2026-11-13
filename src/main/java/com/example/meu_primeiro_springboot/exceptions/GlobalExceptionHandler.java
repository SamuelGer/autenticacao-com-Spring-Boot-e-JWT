package com.example.meu_primeiro_springboot.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleIntegrityViolation(DataIntegrityViolationException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário ou email ja existem. ");
    }
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> handlerInvalidCredentials(InvalidCredentialsException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleArgumentNotValid(MethodArgumentNotValidException e){
        StringBuilder erros = new StringBuilder();
        for (FieldError erro : e.getBindingResult().getFieldErrors()){
            erros.append(erro.getField())
                    .append(": ")
                    .append(erro.getDefaultMessage())
                    .append("; ");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros.toString());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handlerUserNotFound(UsernameNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(SenhasNaoCoincidemException.class)
    public ResponseEntity<String> handlerSenhasNaoCoincidem(SenhasNaoCoincidemException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
