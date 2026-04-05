package com.backend.agendamento.DTOs.mapper;

import com.backend.agendamento.DTOs.request.ServiceRequest;
import com.backend.agendamento.DTOs.response.ServiceResponse;
import com.backend.agendamento.entity.ServiceEntity;
import lombok.experimental.UtilityClass;


@UtilityClass
public class ServiceMapper {


    public ServiceEntity toEntity(ServiceRequest serviceRequest) {
        return ServiceEntity.builder()
                .name(serviceRequest.name())
                .durationMinutes(serviceRequest.durationMinutes())
                .price(serviceRequest.price())
                .active(true) // Novos serviços são criados como ativos por padrão
                .build();
    }


    public ServiceResponse toResponse(ServiceEntity serviceEntity) {
        return ServiceResponse.builder()
                .id(serviceEntity.getId())
                .name(serviceEntity.getName())
                .durationMinutes(serviceEntity.getDurationMinutes())
                .price(serviceEntity.getPrice())
                .active(serviceEntity.getActive())
                .createdAt(serviceEntity.getCreatedAt())
                .updatedAt(serviceEntity.getUpdatedAt())
                .build();
    }


}

