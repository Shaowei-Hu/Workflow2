package com.shaowei.workflow.service.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shaowei.workflow.domain.Client;
import com.shaowei.workflow.service.dto.ClientDTO;

/**
 * Service Interface for managing Client.
 */
public interface ClientServiceCustom {

    /**
     * Save a patient of current user.
     *
     * @param patientDTO the entity of current user to save
     * @return the persisted entity
     */
    ClientDTO save(ClientDTO patientDTO);
    
    /**
     * Save a patient of current user.
     *
     * @param patient the entity to save
     * @return the persisted entity
     */
    Client save(Client patient);

    /**
     * Get all the patients of current user.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ClientDTO> findClientsOfCurrentTrader(Pageable pageable);
}
