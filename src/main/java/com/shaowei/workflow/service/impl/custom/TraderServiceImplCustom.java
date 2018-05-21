package com.shaowei.workflow.service.impl.custom;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shaowei.workflow.domain.Trader;
import com.shaowei.workflow.domain.User;
import com.shaowei.workflow.repository.TraderRepository;
import com.shaowei.workflow.repository.custom.TraderRepositoryCustom;
import com.shaowei.workflow.service.UserService;
import com.shaowei.workflow.service.custom.TraderServiceCustom;
import com.shaowei.workflow.service.dto.TraderDTO;
import com.shaowei.workflow.service.mapper.TraderMapper;

/**
 * Service Implementation for custom code of  Trader.
 */
@Service
@Transactional
public class TraderServiceImplCustom implements TraderServiceCustom {

    private final Logger log = LoggerFactory.getLogger(TraderServiceImplCustom.class);
    
    private final TraderRepository traderRepository;
    private final TraderRepositoryCustom traderRepositoryCustom;
    private final UserService userService;
    
    private final TraderMapper traderMapper;

    public TraderServiceImplCustom(TraderRepository traderRepository,
    TraderRepositoryCustom traderRepositoryCustom,
    UserService userService,
    TraderMapper traderMapper) {
        this.traderRepository = traderRepository;
        this.traderRepositoryCustom = traderRepositoryCustom;
        this.userService = userService;
        this.traderMapper = traderMapper;
    }

    /**
     * Get one doctor by email.
     *
     * @param email the email of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Trader findByName(String email) {
        log.debug("Request to get Trader : {}", email);
        Trader doctor = traderRepositoryCustom.findByName(email);
        return doctor;
    }
    
    /**
     * Get the current doctor dto.
     *
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public TraderDTO findCurrentTraderDTO() {
        log.debug("Request to get current Trader : {}");
        Optional<User> optionalUser = userService.getUserWithAuthorities();
        if(optionalUser.isPresent()){
            String email = optionalUser.get().getEmail();
            Trader doctor = traderRepositoryCustom.findByName(email);
            return traderMapper.toDto(doctor);
        } else {
        	return null;
        }
    }

    /**
     * Get the current doctor.
     *
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Trader findCurrentTrader() {
        log.debug("Request to get current Trader : {}");
        Optional<User> optionalUser = userService.getUserWithAuthorities();
        if(optionalUser.isPresent()){
            String email = optionalUser.get().getLogin();
            return traderRepositoryCustom.findByName(email);
        } else {
        	return null;
        }
    }
}
