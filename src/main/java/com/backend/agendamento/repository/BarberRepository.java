package com.backend.agendamento.repository;

import com.backend.agendamento.entity.Barber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BarberRepository  extends JpaRepository<Barber, Long> {


    public List<Barber> findByNameContainingIgnoreCase(String name);
}
