package com.shaowei.workflow.service.impl;

import com.shaowei.workflow.service.FeedBackService;
import com.shaowei.workflow.domain.FeedBack;
import com.shaowei.workflow.repository.FeedBackRepository;
import com.shaowei.workflow.service.dto.FeedBackDTO;
import com.shaowei.workflow.service.mapper.FeedBackMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing FeedBack.
 */
@Service
@Transactional
public class FeedBackServiceImpl implements FeedBackService {

    private final Logger log = LoggerFactory.getLogger(FeedBackServiceImpl.class);

    private final FeedBackRepository feedBackRepository;

    private final FeedBackMapper feedBackMapper;

    public FeedBackServiceImpl(FeedBackRepository feedBackRepository, FeedBackMapper feedBackMapper) {
        this.feedBackRepository = feedBackRepository;
        this.feedBackMapper = feedBackMapper;
    }

    /**
     * Save a feedBack.
     *
     * @param feedBackDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FeedBackDTO save(FeedBackDTO feedBackDTO) {
        log.debug("Request to save FeedBack : {}", feedBackDTO);
        FeedBack feedBack = feedBackMapper.toEntity(feedBackDTO);
        feedBack = feedBackRepository.save(feedBack);
        return feedBackMapper.toDto(feedBack);
    }

    /**
     * Get all the feedBacks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FeedBackDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FeedBacks");
        return feedBackRepository.findAll(pageable)
            .map(feedBackMapper::toDto);
    }


    /**
     * Get one feedBack by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FeedBackDTO> findOne(Long id) {
        log.debug("Request to get FeedBack : {}", id);
        return feedBackRepository.findById(id)
            .map(feedBackMapper::toDto);
    }

    /**
     * Delete the feedBack by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FeedBack : {}", id);
        feedBackRepository.deleteById(id);
    }
}
