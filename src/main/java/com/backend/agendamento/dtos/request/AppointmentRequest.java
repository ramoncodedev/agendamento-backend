package com.backend.agendamento.DTOs.request;

import com.backend.agendamento.entity.AppointmentStatus;
import com.backend.agendamento.entity.Barber;
import com.backend.agendamento.entity.Customer;
import com.backend.agendamento.entity.ServiceEntity;

import java.time.LocalDateTime;

public record AppointmentRequest( Long customerId,
                                  Long barberId,
                                  Long serviceId,
                                  LocalDateTime startAt,
                                  String notes
                                 ) {
}
