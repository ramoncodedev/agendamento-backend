package com.backend.agendamento.repository;

import com.backend.agendamento.entity.Barber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BarberRepository  extends JpaRepository<Barber, Long> {
}
