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

import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.DayOfWeek;

/**
 * Entidade que representa a agenda (horário de funcionamento) de um barbeiro.
 * Define os dias e horários em que um barbeiro está disponível.
 *
 * Exemplo:
 * - Barbeiro: João
 * - Segunda a Sexta: 08:00 - 18:00
 * - Sábado: 08:00 - 14:00
 * - Domingo: Fechado
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(
    name = "barber_schedules",
    indexes = {
        @Index(name = "idx_barber_weekday", columnList = "barber_id, weekday"),
        @Index(name = "idx_barber_active", columnList = "barber_id, active")
    },
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_barber_weekday",
            columnNames = {"barber_id", "weekday"}
        )
    }
)
public class BarberSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "barber_id", nullable = false)
    private Barber barber;

    /**
     * Dia da semana (1 = Segunda, 2 = Terça, ..., 6 = Sábado, 7 = Domingo)
     * Conforme padrão DayOfWeek do Java (MONDAY=1, SUNDAY=7)
     */
    @NotNull(message = "O dia da semana é obrigatório\n")
    @Min(value = 1, message = "Weekday must be between 1 (Monday) and 7 (Sunday)")
    @Max(value = 7, message = "Weekday must be between 1 (Monday) and 7 (Sunday)")
    @Column(nullable = false)
    private Integer weekday;

    @NotNull(message = "O horário de início é obrigatório.")
    @Column(nullable = false)
    private LocalTime startTime;

    @NotNull(message = "O horário de término é obrigatório")
    @Column(nullable = false)
    private LocalTime endTime;

    @Builder.Default
    @Column(nullable = false)
    private Boolean active = true;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    /**
     * Valida se o horário de término é posterior ao de início.
     * Esta validação é executada antes de persistir a entidade.
     */
    @PrePersist
    @PreUpdate
    public void validateSchedule() {
        if (startTime != null && endTime != null) {
            if (endTime.isBefore(startTime) || endTime.equals(startTime)) {
                throw new IllegalArgumentException(
                    "O horário de término deve ser posterior ao horário de início."
                );
            }
        }
    }

    /**
     * Retorna o nome do dia da semana.
     */
    public String getWeekdayName() {
        DayOfWeek day = DayOfWeek.of(this.weekday);
        return day.toString();
    }
}

