package com.example.meu_primeiro_springboot.exceptions;

public class SenhasNaoCoincidemException extends RuntimeException{
    public SenhasNaoCoincidemException(String message){
        super(message);
    }
}
