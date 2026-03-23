package com.backend.agendamento.repository;

import com.backend.agendamento.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository para gerenciar persistência de Serviços.
 * Fornece operações CRUD e queries customizadas para ServiceEntity.
 */
@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {

    /**
     * Busca um serviço pelo nome (case-insensitive).
     * 
     * @param name o nome do serviço
     * @return Optional contendo o serviço se encontrado
     */
    Optional<ServiceEntity> findByNameIgnoreCase(String name);

    /**
     * Busca serviços que contenham um padrão no nome (case-insensitive).
     * Útil para busca parcial.
     * 
     * @param name padrão do nome a buscar
     * @return lista de serviços encontrados
     */
    List<ServiceEntity> findByNameContainingIgnoreCase(String name);

    /**
     * Lista todos os serviços ativos.
     * 
     * @return lista de serviços com active = true
     */
    List<ServiceEntity> findByActiveTrue();

    /**
     * Lista todos os serviços inativos.
     * 
     * @return lista de serviços com active = false
     */
    List<ServiceEntity> findByActiveFalse();

    /**
     * Verifica se existe um serviço com o nome especificado.
     * 
     * @param name o nome do serviço
     * @return true se existe, false caso contrário
     */
    boolean existsByNameIgnoreCase(String name);
}

