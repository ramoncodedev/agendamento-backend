package com.backend.agendamento.DTOs.mapper;

import com.backend.agendamento.DTOs.request.ServiceRequest;
import com.backend.agendamento.DTOs.response.ServiceResponse;
import com.backend.agendamento.entity.ServiceEntity;
import lombok.experimental.UtilityClass;

/**
 * Mapper para conversão entre ServiceEntity, ServiceRequest e ServiceResponse.
 * Utiliza padrão UtilityClass do Lombok para criar uma classe utilitária imutável.
 */
@UtilityClass
public class ServiceMapper {

    /**
     * Converte um ServiceRequest para ServiceEntity.
     * 
     * @param serviceRequest DTO de requisição com dados do serviço
     * @return ServiceEntity com os dados do request
     */
    public ServiceEntity toEntity(ServiceRequest serviceRequest) {
        return ServiceEntity.builder()
                .name(serviceRequest.name())
                .durationMinutes(serviceRequest.durationMinutes())
                .price(serviceRequest.price())
                .active(true) // Novos serviços são criados como ativos por padrão
                .build();
    }

    /**
     * Converte um ServiceEntity para ServiceResponse.
     * 
     * @param serviceEntity entidade do serviço
     * @return DTO de resposta com dados do serviço
     */
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

    /**
     * Atualiza uma ServiceEntity existente com dados de um ServiceRequest.
     * Mantém o ID e timestamps inalterados.
     * 
     * @param serviceRequest DTO de requisição com novos dados
     * @param existingService entidade existente a ser atualizada
     * @return ServiceEntity atualizada
     */
    public ServiceEntity updateEntity(ServiceRequest serviceRequest, ServiceEntity existingService) {
        existingService.setName(serviceRequest.name());
        existingService.setDurationMinutes(serviceRequest.durationMinutes());
        existingService.setPrice(serviceRequest.price());
        return existingService;
    }
}

