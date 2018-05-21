package com.shaowei.workflow.service;

import com.shaowei.workflow.service.dto.FeedBackDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing FeedBack.
 */
public interface FeedBackService {

    /**
     * Save a feedBack.
     *
     * @param feedBackDTO the entity to save
     * @return the persisted entity
     */
    FeedBackDTO save(FeedBackDTO feedBackDTO);

    /**
     * Get all the feedBacks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FeedBackDTO> findAll(Pageable pageable);


    /**
     * Get the "id" feedBack.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FeedBackDTO> findOne(Long id);

    /**
     * Delete the "id" feedBack.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
