package com.backend.agendamento.DTOs.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO para resposta de Serviços.
 */
@Builder
public record ServiceResponse(
        Long id,
        String name,
        Long durationMinutes,
        BigDecimal price,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
