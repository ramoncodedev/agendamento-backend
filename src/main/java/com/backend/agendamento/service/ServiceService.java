package com.backend.agendamento.service;
import com.backend.agendamento.entity.ServiceEntity;
import com.backend.agendamento.exception.ConflictException;
import com.backend.agendamento.exception.NoBarbersAvailableException;
import com.backend.agendamento.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ServiceService {
    private final ServiceRepository serviceRepository;


    public ServiceEntity saveService(ServiceEntity serviceEntity) {
        verifyServiceNameAvailability(serviceEntity.getName());
        return serviceRepository.save(serviceEntity);
    }
    public ServiceEntity updateService(Long id, ServiceEntity serviceEntity) {
        ServiceEntity existingService = findServiceById(id);
        if (!existingService.getName().equalsIgnoreCase(serviceEntity.getName())) {
            verifyServiceNameAvailability(serviceEntity.getName());
        }
        existingService.setName(serviceEntity.getName());
        existingService.setDurationMinutes(serviceEntity.getDurationMinutes());
        existingService.setPrice(serviceEntity.getPrice());
        existingService.setActive(serviceEntity.getActive());
        return serviceRepository.save(existingService);
    }
    public ServiceEntity findServiceById(Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new NoBarbersAvailableException("Service with id " + id + " not found."));
    }
    public List<ServiceEntity> findAll() {
        List<ServiceEntity> services = serviceRepository.findAll();
        if (services.isEmpty()) {
            throw new NoBarbersAvailableException("There are no services available.");
        }
        return services;
    }
    public List<ServiceEntity> findAllActive() {
        List<ServiceEntity> activeServices = serviceRepository.findByActiveTrue();
        if (activeServices.isEmpty()) {
            throw new NoBarbersAvailableException("There are no active services available.");
        }
        return activeServices;
    }
    public List<ServiceEntity> findByName(String name) {
        List<ServiceEntity> services = serviceRepository.findByNameContainingIgnoreCase(name);
        if (services.isEmpty()) {
            throw new NoBarbersAvailableException("No services found with name containing: " + name);
        }
        return services;
    }
    public void deleteService(Long id) {
        ServiceEntity service = findServiceById(id);
        serviceRepository.deleteById(id);
    }
    public ServiceEntity deactivateService(Long id) {
        ServiceEntity service = findServiceById(id);
        service.setActive(false);
        return serviceRepository.save(service);
    }
    public ServiceEntity activateService(Long id) {
        ServiceEntity service = findServiceById(id);
        service.setActive(true);
        return serviceRepository.save(service);
    }
    private void verifyServiceNameAvailability(String name) {
        if (serviceRepository.existsByNameIgnoreCase(name)) {
            throw new ConflictException("A service with the name '" + name + "' already exists.");
        }
    }
}
