package com.backend.agendamento.DTOs.response;

import com.backend.agendamento.entity.AppointmentStatus;
import com.backend.agendamento.entity.Customer;
import com.backend.agendamento.entity.ServiceEntity;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AppointmentResponse (Long id,
                                   Customer customer,
                                   ServiceEntity service,
                                   LocalDateTime startAt,
                                   LocalDateTime endAt,
                                   String notes,
                                   AppointmentStatus status,
                                   LocalDateTime createdAt,
                                   LocalDateTime updateAt
                                   ){
}
