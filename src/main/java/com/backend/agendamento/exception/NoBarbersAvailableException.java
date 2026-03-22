package com.backend.agendamento.exception;

public class NoBarbersAvailableException  extends RuntimeException{

    public NoBarbersAvailableException(String message){
        super(message);
    }
}
