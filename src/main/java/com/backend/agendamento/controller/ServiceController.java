package com.backend.agendamento.controller;

import com.backend.agendamento.DTOs.mapper.ServiceMapper;
import com.backend.agendamento.DTOs.request.ServiceRequest;
import com.backend.agendamento.DTOs.response.ServiceResponse;
import com.backend.agendamento.entity.ServiceEntity;
import com.backend.agendamento.service.ServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST para gerenciar Serviços.
 * Fornece endpoints para criar, ler, atualizar e deletar serviços.
 */
@RestController
@RequestMapping("/api/v1/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceService serviceService;

    /**
     * Cria um novo serviço.
     * 
     * @param serviceRequest dados do novo serviço
     * @return ResponseEntity com o serviço criado e status 201
     */
    @PostMapping
    public ResponseEntity<ServiceResponse> createService( @Valid @RequestBody ServiceRequest serviceRequest) {
        ServiceEntity serviceEntity = ServiceMapper.toEntity(serviceRequest);
        ServiceEntity savedService = serviceService.saveService(serviceEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(ServiceMapper.toResponse(savedService));
    }

    /**
     * Recupera um serviço pelo ID.
     * 
     * @param id identificador do serviço
     * @return ResponseEntity com o serviço encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse> getServiceById(@PathVariable Long id) {
        ServiceEntity service = serviceService.findServiceById(id);
        ServiceResponse response = ServiceMapper.toResponse(service);
        return ResponseEntity.ok(response);
    }

    /**
     * Lista todos os serviços.
     * 
     * @return ResponseEntity com lista de todos os serviços
     */
    @GetMapping
    public ResponseEntity<List<ServiceResponse>> getAllServices() {
        List<ServiceEntity> services = serviceService.findAll();
        List<ServiceResponse> responses = services.stream()
                .map(ServiceMapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    /**
     * Lista apenas os serviços ativos.
     * 
     * @return ResponseEntity com lista de serviços ativos
     */
    @GetMapping("/active")
    public ResponseEntity<List<ServiceResponse>> getActiveServices() {
        List<ServiceEntity> activeServices = serviceService.findAllActive();
        List<ServiceResponse> responses = activeServices.stream()
                .map(ServiceMapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    /**
     * Busca serviços pelo nome (busca parcial).
     * 
     * @param name padrão do nome a buscar
     * @return ResponseEntity com lista de serviços encontrados
     */
    @GetMapping("/search")
    public ResponseEntity<List<ServiceResponse>> searchServicesByName(
            @RequestParam String name) {
        List<ServiceEntity> services = serviceService.findByName(name);
        List<ServiceResponse> responses = services.stream()
                .map(ServiceMapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    /**
     * Atualiza um serviço existente.
     * 
     * @param id identificador do serviço a atualizar
     * @param serviceRequest novos dados do serviço
     * @return ResponseEntity com o serviço atualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponse> updateService( @PathVariable Long id, @Valid @RequestBody ServiceRequest serviceRequest) {
        ServiceEntity serviceEntity = ServiceMapper.toEntity(serviceRequest);
        ServiceEntity updatedService = serviceService.updateService(id, serviceEntity);
        ServiceResponse response = ServiceMapper.toResponse(updatedService);
        return ResponseEntity.ok(response);
    }

    /**
     * Deleta um serviço.
     * 
     * @param id identificador do serviço a deletar
     * @return ResponseEntity com status 204 (No Content)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Ativa um serviço inativo.
     * 
     * @param id identificador do serviço a ativar
     * @return ResponseEntity com o serviço ativado
     */
    @PatchMapping("/{id}/activate")
    public ResponseEntity<ServiceResponse> activateService(@PathVariable Long id) {
        ServiceEntity activatedService = serviceService.activateService(id);
        ServiceResponse response = ServiceMapper.toResponse(activatedService);
        return ResponseEntity.ok(response);
    }

    /**
     * Desativa um serviço ativo.
     * 
     * @param id identificador do serviço a desativar
     * @return ResponseEntity com o serviço desativado
     */
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ServiceResponse> deactivateService(@PathVariable Long id) {
        ServiceEntity deactivatedService = serviceService.deactivateService(id);
        ServiceResponse response = ServiceMapper.toResponse(deactivatedService);
        return ResponseEntity.ok(response);
    }
}


