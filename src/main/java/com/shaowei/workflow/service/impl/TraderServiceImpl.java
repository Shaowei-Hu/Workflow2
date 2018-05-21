package com.shaowei.workflow.service.impl;

import com.shaowei.workflow.service.TraderService;
import com.shaowei.workflow.domain.Trader;
import com.shaowei.workflow.repository.TraderRepository;
import com.shaowei.workflow.service.dto.TraderDTO;
import com.shaowei.workflow.service.mapper.TraderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Trader.
 */
@Service
@Transactional
public class TraderServiceImpl implements TraderService {

    private final Logger log = LoggerFactory.getLogger(TraderServiceImpl.class);

    private final TraderRepository traderRepository;

    private final TraderMapper traderMapper;

    public TraderServiceImpl(TraderRepository traderRepository, TraderMapper traderMapper) {
        this.traderRepository = traderRepository;
        this.traderMapper = traderMapper;
    }

    /**
     * Save a trader.
     *
     * @param traderDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TraderDTO save(TraderDTO traderDTO) {
        log.debug("Request to save Trader : {}", traderDTO);
        Trader trader = traderMapper.toEntity(traderDTO);
        trader = traderRepository.save(trader);
        return traderMapper.toDto(trader);
    }

    /**
     * Get all the traders.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TraderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Traders");
        return traderRepository.findAll(pageable)
            .map(traderMapper::toDto);
    }


    /**
     * Get one trader by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TraderDTO> findOne(Long id) {
        log.debug("Request to get Trader : {}", id);
        return traderRepository.findById(id)
            .map(traderMapper::toDto);
    }

    /**
     * Delete the trader by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Trader : {}", id);
        traderRepository.deleteById(id);
    }
}
