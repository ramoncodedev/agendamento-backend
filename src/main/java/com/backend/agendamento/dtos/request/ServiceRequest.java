package com.backend.agendamento.DTOs.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

/**
 * DTO para requisição de criação/atualização de Serviços.
 */
public record ServiceRequest(

        @NotBlank(message = "O nome do serviço não pode estar vazio")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String name,

        @NotNull(message = "A duração do serviço é obrigatória")
        @Positive(message = "A duração deve ser um valor positivo")
        Long durationMinutes,

        @NotNull(message = "O preço do serviço é obrigatório")
        @DecimalMin(value = "0.01", message = "O preço deve ser maior que 0")
        BigDecimal price

) {
}

