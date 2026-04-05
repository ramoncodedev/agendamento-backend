package com.backend.agendamento.dtos.response;

import lombok.Builder;

@Builder
public record UserResponse(Long id,
                           String name,
                           String email,
                           String password) {
}
