package com.backend.agendamento.DTOs.response;

import com.backend.agendamento.entity.Barber;
import lombok.Builder;

import java.time.LocalTime;


@Builder
public record BarberScheduleResponse(Long id,
                                     Barber barber,
                                     Integer weekday,
                                     LocalTime startTime,
                                     LocalTime endTime,
                                     Boolean active) {
}
