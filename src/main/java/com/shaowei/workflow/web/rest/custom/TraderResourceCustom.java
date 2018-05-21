package com.shaowei.workflow.web.rest.custom;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.shaowei.workflow.domain.Trader;
import com.shaowei.workflow.service.custom.TraderServiceCustom;
import com.shaowei.workflow.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing City.
 */
@RestController
@RequestMapping("/api")
public class TraderResourceCustom {

    private final Logger log = LoggerFactory.getLogger(TraderResourceCustom.class);

    private final TraderServiceCustom traderServiceCustom;

    public TraderResourceCustom(TraderServiceCustom traderServiceCustom) {
        this.traderServiceCustom = traderServiceCustom;
    }
    
    /**
     * SEARCH  /_search/trader/byCode/{inseeCode} : search for the city by insee code
     * 
     *
     * @param inseeCode the insee code of the Trader search
     * @return the result of the search, the ResponseEntity with status 200 (OK) and with body the Trader, or with status 404 (Not Found)
     */
    @GetMapping("/trader/byCode/{inseeCode}")
    @Timed
    public ResponseEntity<Trader> searchCitiesByCode(@PathVariable String inseeCode) {
        log.debug("REST request to search for a page of Traders for inseeCode {}", inseeCode);
        Trader trader = traderServiceCustom.findByName(inseeCode);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(trader));
    }

}
