package com.shaowei.workflow.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.shaowei.workflow.service.TraderService;
import com.shaowei.workflow.web.rest.errors.BadRequestAlertException;
import com.shaowei.workflow.web.rest.util.HeaderUtil;
import com.shaowei.workflow.web.rest.util.PaginationUtil;
import com.shaowei.workflow.service.dto.TraderDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Trader.
 */
@RestController
@RequestMapping("/api")
public class TraderResource {

    private final Logger log = LoggerFactory.getLogger(TraderResource.class);

    private static final String ENTITY_NAME = "trader";

    private final TraderService traderService;

    public TraderResource(TraderService traderService) {
        this.traderService = traderService;
    }

    /**
     * POST  /traders : Create a new trader.
     *
     * @param traderDTO the traderDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new traderDTO, or with status 400 (Bad Request) if the trader has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/traders")
    @Timed
    public ResponseEntity<TraderDTO> createTrader(@RequestBody TraderDTO traderDTO) throws URISyntaxException {
        log.debug("REST request to save Trader : {}", traderDTO);
        if (traderDTO.getId() != null) {
            throw new BadRequestAlertException("A new trader cannot already have an ID", ENTITY_NAME, "idexists");
        }        
        TraderDTO result = traderService.save(traderDTO);
        return ResponseEntity.created(new URI("/api/traders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /traders : Updates an existing trader.
     *
     * @param traderDTO the traderDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated traderDTO,
     * or with status 400 (Bad Request) if the traderDTO is not valid,
     * or with status 500 (Internal Server Error) if the traderDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/traders")
    @Timed
    public ResponseEntity<TraderDTO> updateTrader(@RequestBody TraderDTO traderDTO) throws URISyntaxException {
        log.debug("REST request to update Trader : {}", traderDTO);
        if (traderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }        
        TraderDTO result = traderService.save(traderDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, traderDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /traders : get all the traders.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of traders in body
     */
    @GetMapping("/traders")
    @Timed
    public ResponseEntity<List<TraderDTO>> getAllTraders(Pageable pageable) {
        log.debug("REST request to get a page of Traders");
        Page<TraderDTO> page = traderService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/traders");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /traders/:id : get the "id" trader.
     *
     * @param id the id of the traderDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the traderDTO, or with status 404 (Not Found)
     */
    @GetMapping("/traders/{id}")
    @Timed
    public ResponseEntity<TraderDTO> getTrader(@PathVariable Long id) {
        log.debug("REST request to get Trader : {}", id);
        Optional<TraderDTO> traderDTO = traderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(traderDTO);
    }

    /**
     * DELETE  /traders/:id : delete the "id" trader.
     *
     * @param id the id of the traderDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/traders/{id}")
    @Timed
    public ResponseEntity<Void> deleteTrader(@PathVariable Long id) {
        log.debug("REST request to delete Trader : {}", id);
        traderService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
