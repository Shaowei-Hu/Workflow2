package com.shaowei.workflow.service;

import com.shaowei.workflow.service.dto.TypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Type.
 */
public interface TypeService {

    /**
     * Save a type.
     *
     * @param typeDTO the entity to save
     * @return the persisted entity
     */
    TypeDTO save(TypeDTO typeDTO);

    /**
     * Get all the types.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" type.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TypeDTO> findOne(Long id);

    /**
     * Delete the "id" type.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
