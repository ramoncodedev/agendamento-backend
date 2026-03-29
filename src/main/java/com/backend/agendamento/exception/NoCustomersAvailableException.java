package com.backend.agendamento.exception;

public class NoCustomersAvailableException extends RuntimeException {

    public NoCustomersAvailableException(String message){
        super(message);
    }
}
