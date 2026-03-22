package com.backend.agendamento.DTOs.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CustomerResponse(Long id,
                               String name,
                               String phone,
                               String email,
                               LocalDateTime createdAt) {
}
