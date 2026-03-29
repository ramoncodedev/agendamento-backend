package com.backend.agendamento.service;


import com.backend.agendamento.entity.Barber;
import com.backend.agendamento.exception.NoBarbersAvailableException;
import com.backend.agendamento.repository.BarberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BarberService {

    private final BarberRepository barberRepository;

    @Transactional
    public Barber saveBarber(Barber barber){
        return barberRepository.save(barber);
    }

    @Transactional(readOnly = true)
    public List<Barber> findAll(){
        List<Barber> barbers = barberRepository.findAll();

        if (barbers.isEmpty()){
            throw new NoBarbersAvailableException("There are no barbers available.");
        }

        return barbers;
    }

    @Transactional(readOnly = true)
    public  List<Barber> findByName(String name){
            List<Barber> findBarber = barberRepository.findByNameContainingIgnoreCase(name);

            if (findBarber.isEmpty()){
                throw new NoBarbersAvailableException("There is no barber with that name.");
            }

            return findBarber;
    }

    @Transactional
    public void deleteBarber(Long id){
        Barber barber = barberRepository.findById(id)
                .orElseThrow(()-> new NoBarbersAvailableException("There is no barber with that id."));

        barberRepository.deleteById(id);
    }
}
