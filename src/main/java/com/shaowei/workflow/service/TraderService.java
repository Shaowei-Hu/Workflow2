package com.shaowei.workflow.service;

import com.shaowei.workflow.service.dto.TraderDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Trader.
 */
public interface TraderService {

    /**
     * Save a trader.
     *
     * @param traderDTO the entity to save
     * @return the persisted entity
     */
    TraderDTO save(TraderDTO traderDTO);

    /**
     * Get all the traders.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TraderDTO> findAll(Pageable pageable);


    /**
     * Get the "id" trader.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TraderDTO> findOne(Long id);

    /**
     * Delete the "id" trader.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
