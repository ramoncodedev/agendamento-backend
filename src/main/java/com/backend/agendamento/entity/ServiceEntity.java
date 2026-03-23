package com.backend.agendamento.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * Entidade que representa um Serviço oferecido pela barbearia.
 * Exemplos: corte de cabelo, barba, hidratação, etc.
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "services")
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome do serviço (ex: "Corte de Cabelo")
     */
    @NotBlank(message = "O nome do serviço não pode estar vazio")
    @Size(min = 3, max = 100, message = "O nome do serviço deve ter entre 3 e 100 caracteres")
    @Column(nullable = false, length = 100)
    private String name;

    /**
     * Duração do serviço em minutos
     */
    @NotNull(message = "A duração do serviço é obrigatória")
    @Positive(message = "A duração deve ser um valor positivo")
    @Column(nullable = false, name = "duration_minutes")
    private Long durationMinutes;

    /**
     * Preço do serviço com precisão de centavos
     */
    @NotNull(message = "O preço do serviço é obrigatório")
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que 0")
    @Column(nullable = false, precision = 10, scale = 2) // Bigdecimal com 10 digitos e 2 casa decimais
    private BigDecimal price;

    /**
     * Flag para controlar se o serviço está ativo ou inativo
     */
    @Column(nullable = false)
    private Boolean active = true;

    /**
     * Data e hora de criação do registro (preenchida automaticamente)
     */
    @CreationTimestamp
    @Column(nullable = false, updatable = false, name = "created_at")
    private LocalDateTime createdAt;

    /**
     * Data e hora da última atualização (preenchida automaticamente)
     */
    @UpdateTimestamp
    @Column(nullable = false, name = "updated_at")
    private LocalDateTime updatedAt;
}
