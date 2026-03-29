package com.backend.agendamento.repository;


import com.backend.agendamento.entity.Barber;
import com.backend.agendamento.entity.BarberSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarberScheduleRepository extends JpaRepository<BarberSchedule, Long> {

    public List<BarberSchedule> findByBarberId(Long barberId);
}
