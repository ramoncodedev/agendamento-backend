package com.backend.agendamento.repository;

import com.backend.agendamento.entity.Appointment;
import com.backend.agendamento.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    /**
     * Busca todos os agendamentos de um cliente específico
     * @param customer o cliente
     * @return lista de agendamentos do cliente
     */
    List<Appointment> findByCustomer(Customer customer);

    /**
     * Busca agendamentos pelo nome do cliente (usando JPQL)
     * @param customerName nome do cliente
     * @return lista de agendamentos encontrados
     */
    @Query("SELECT a FROM Appointment a WHERE LOWER(a.customer.name) LIKE LOWER(CONCAT('%', :customerName, '%'))")
    List<Appointment> findByCustomerName(@Param("customerName") String customerName);

    boolean existsByBarberIdAndStartAtLessThanAndEndAtGreaterThan(Long appointment, LocalDateTime start, LocalDateTime end);

}
