package com.backend.agendamento.DTOs.request;

import com.backend.agendamento.entity.Barber;

import java.time.LocalDate;
import java.time.LocalTime;

public record BarberScheduleRequest(Barber barber,
                                    Integer weekday,
                                    LocalTime startTime,
                                    LocalTime endTime,
                                    Boolean active) {
}
