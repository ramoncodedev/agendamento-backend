package com.backend.agendamento.DTOs.response;

import lombok.Builder;

@Builder
public record BarberResponse(Long id,
                             String name,
                             boolean active) {
}
