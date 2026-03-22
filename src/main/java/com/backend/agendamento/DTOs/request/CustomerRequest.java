package com.backend.agendamento.DTOs.request;

import java.time.LocalDateTime;

public record CustomerRequest(String name,
                              String phone,
                              String email) {
}
