package com.backend.agendamento.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.Duration;

/**
 * Entidade que representa um agendamento de serviço em uma barbearia.
 * Associa um cliente a um barbeiro e um serviço em um horário específico.
 *
 * Exemplo:
 * - Cliente: José Silva
 * - Barbeiro: João
 * - Serviço: Corte de Cabelo
 * - Data/Hora: 2025-03-25 10:00
 * - Status: SCHEDULED (Agendado)
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(
    name = "appointments",
    indexes = {
        @Index(name = "idx_appointment_barber_date", columnList = "barber_id, start_at"),
        @Index(name = "idx_appointment_customer_date", columnList = "customer_id, start_at"),
        @Index(name = "idx_appointment_status", columnList = "status"),
        @Index(name = "idx_appointment_barber_status", columnList = "barber_id, status")
    },
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_barber_appointment_time",
            columnNames = {"barber_id", "start_at", "end_at"}
        )
    }
)
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Customer is required")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @NotNull(message = "Barber is required")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "barber_id", nullable = false)
    private Barber barber;

    @NotNull(message = "Service is required")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_id", nullable = false)
    private ServiceEntity service;

    @NotNull(message = "Start date is required")
    @Future(message = "Appointment must be scheduled for a future date")
    @Column(name = "start_at", nullable = false)
    private LocalDateTime startAt;

    @NotNull(message = "End date is required")
    @Column(name = "end_at", nullable = false)
    private LocalDateTime endAt;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private AppointmentStatus status = AppointmentStatus.SCHEDULED;

    @Size(max = 500, message = "Notes cannot exceed 500 characters")
    @Column(columnDefinition = "TEXT")
    private String notes;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    /**
     * Valida os horários do agendamento antes de persistir.
     * Garante que:
     * - Horário de fim é posterior ao de início
     * - Não há conflito de horários
     */
    @PrePersist
    @PreUpdate
    public void validateAppointment() {
        if (startAt != null && endAt != null) {
            if (endAt.isBefore(startAt) || endAt.equals(startAt)) {
                throw new IllegalArgumentException(
                    "Appointment end time must be after start time"
                );
            }

            // Valida se a data é futura
            if (startAt.isBefore(LocalDateTime.now())) {
                throw new IllegalArgumentException(
                    "Appointment cannot be scheduled in the past"
                );
            }
        }
    }

    /**
     * Calcula a duração do agendamento em minutos.
     * @return duração em minutos
     */
    public long getDurationInMinutes() {
        if (startAt == null || endAt == null) {
            return 0;
        }
        return Duration.between(startAt, endAt).toMinutes();
    }

    /**
     * Verifica se há conflito de horários com outro agendamento.
     * Dois agendamentos conflitam se seus períodos se sobrepõem.
     *
     * @param other outro agendamento
     * @return true se há conflito
     */
    public boolean hasConflictWith(Appointment other) {
        if (other == null || other.startAt == null || other.endAt == null) {
            return false;
        }
        // Conflito se: A.startAt < B.endAt AND B.startAt < A.endAt
        return this.startAt.isBefore(other.endAt) && 
               other.startAt.isBefore(this.endAt);
    }

    /**
     * Verifica se o agendamento pode ser modificado.
     * Apenas agendamentos com status SCHEDULED podem ser modificados.
     *
     * @return true se pode ser modificado
     */
    public boolean canBeModified() {
        return status == AppointmentStatus.SCHEDULED;
    }

    /**
     * Verifica se o agendamento pode ser cancelado.
     * Apenas agendamentos SCHEDULED e COMPLETED podem ser cancelados.
     *
     * @return true se pode ser cancelado
     */
    public boolean canBeCanceled() {
        return status == AppointmentStatus.SCHEDULED || 
               status == AppointmentStatus.COMPLETED;
    }
}
