package com.example.meu_primeiro_springboot.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String message){
        super(message);
    }
}
