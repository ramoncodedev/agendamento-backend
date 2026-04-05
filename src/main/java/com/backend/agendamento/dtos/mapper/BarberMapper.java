package com.backend.agendamento.DTOs.mapper;

import com.backend.agendamento.DTOs.request.BarberRequest;
import com.backend.agendamento.DTOs.response.BarberResponse;
import com.backend.agendamento.entity.Barber;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BarberMapper {

    public Barber toDtoRequest(BarberRequest barberRequest){
        return Barber.builder()
                .name(barberRequest.name())
                .active(barberRequest.active())
                .build();
    }

    public BarberResponse toDtoResponse(Barber barber){
        return BarberResponse.builder()
                .id(barber.getId())
                .name(barber.getName())
                .active(barber.getActive())
                .build();
    }
}
