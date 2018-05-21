package com.shaowei.workflow.service.custom;

import com.shaowei.workflow.domain.Trader;
import com.shaowei.workflow.service.dto.TraderDTO;

/**
 * Service Interface for managing Trader.
 */
public interface TraderServiceCustom {

    /**
     * Get the "email" doctor.
     *
     * @param email the email of the entity
     * @return the entity
     */
    Trader findByName(String name);
    
    /**
     * Get the current connected doctor dto.
     *
     * @return the entity
     */
    TraderDTO findCurrentTraderDTO();
    
    /**
     * Get the current connected doctor.
     *
     * @return the entity
     */
    Trader findCurrentTrader();
}
