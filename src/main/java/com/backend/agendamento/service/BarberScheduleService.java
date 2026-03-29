package com.backend.agendamento.service;

import com.backend.agendamento.entity.Barber;
import com.backend.agendamento.entity.BarberSchedule;
import com.backend.agendamento.repository.BarberScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BarberScheduleService {

    private final BarberScheduleRepository scheduleRepository;

    @Transactional
    public BarberSchedule saveSchedule(BarberSchedule barberSchedule){
        return scheduleRepository.save(barberSchedule);
    }

    @Transactional(readOnly = true)
    public List<BarberSchedule> findByBarberId(Long barberId){
       List<BarberSchedule> barberSchedules = scheduleRepository.findByBarberId(barberId);
       return barberSchedules;
    }

    @Transactional(readOnly = true)
    public List<BarberSchedule> findAllSchedule(){
        return scheduleRepository.findAll();
    }

    @Transactional
    public void deleteSheduleId(Long id){
        scheduleRepository.deleteById(id);
    }




}
