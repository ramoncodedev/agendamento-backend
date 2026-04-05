package com.backend.agendamento.exception;

public class NoExistAppointmentException extends RuntimeException {

    public NoExistAppointmentException(String message){
        super(message);
    }
}
