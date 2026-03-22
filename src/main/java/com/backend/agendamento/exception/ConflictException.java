package com.backend.agendamento.exception;

public class ConflictException  extends RuntimeException{

    public  ConflictException(String message){
        super(message);
    }
}
