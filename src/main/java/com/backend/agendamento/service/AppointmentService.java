package com.backend.agendamento.service;

import com.backend.agendamento.entity.Appointment;

import com.backend.agendamento.exception.NoBarbersAvailableException;
import com.backend.agendamento.exception.NoCustomersAvailableException;
import com.backend.agendamento.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;


    @Transactional
    public Appointment saveAppointment(Appointment appointment){


        LocalDateTime start = appointment.getStartAt();
        LocalDateTime end = appointment.getEndAt();

        boolean conflito = appointmentRepository
                .existsByBarberIdAndStartAtLessThanAndEndAtGreaterThan(
                        appointment.getBarber().getId(), end, start
                );

        return appointmentRepository.save(appointment);
    }


    @Transactional(readOnly = true)
    public List<Appointment> findAllAppointments(){
        List<Appointment> appointments = appointmentRepository.findAll();

        if (appointments.isEmpty()){
            throw new NoCustomersAvailableException("Não há horários disponíveis.");
        }

        return appointments;
    }


    @Transactional(readOnly = true)
    public List<Appointment> findByCustomerName(String customerName){
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new NoCustomersAvailableException("O nome do cliente não pode estar vazio.");
        }
        return appointmentRepository.findByCustomerName(customerName.trim());
    }
}
