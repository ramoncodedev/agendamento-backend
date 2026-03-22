package com.backend.agendamento.service;


import com.backend.agendamento.entity.Barber;
import com.backend.agendamento.exception.NoBarbersAvailableException;
import com.backend.agendamento.repository.BarberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BarberService {

    private final BarberRepository barberRepository;

    public Barber saveBarber(Barber barber){
        return barberRepository.save(barber);
    }

    public List<Barber> findAll(){
        List<Barber> barbers = barberRepository.findAll();

        if (barbers.isEmpty()){
            throw new NoBarbersAvailableException("There are no barbers available.");
        }

        return barbers;
    }
}
