package com.shaowei.workflow.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.shaowei.workflow.service.FeedBackService;
import com.shaowei.workflow.web.rest.errors.BadRequestAlertException;
import com.shaowei.workflow.web.rest.util.HeaderUtil;
import com.shaowei.workflow.web.rest.util.PaginationUtil;
import com.shaowei.workflow.service.dto.FeedBackDTO;
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
 * REST controller for managing FeedBack.
 */
@RestController
@RequestMapping("/api")
public class FeedBackResource {

    private final Logger log = LoggerFactory.getLogger(FeedBackResource.class);

    private static final String ENTITY_NAME = "feedBack";

    private final FeedBackService feedBackService;

    public FeedBackResource(FeedBackService feedBackService) {
        this.feedBackService = feedBackService;
    }

    /**
     * POST  /feed-backs : Create a new feedBack.
     *
     * @param feedBackDTO the feedBackDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new feedBackDTO, or with status 400 (Bad Request) if the feedBack has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/feed-backs")
    @Timed
    public ResponseEntity<FeedBackDTO> createFeedBack(@RequestBody FeedBackDTO feedBackDTO) throws URISyntaxException {
        log.debug("REST request to save FeedBack : {}", feedBackDTO);
        if (feedBackDTO.getId() != null) {
            throw new BadRequestAlertException("A new feedBack cannot already have an ID", ENTITY_NAME, "idexists");
        }        
        FeedBackDTO result = feedBackService.save(feedBackDTO);
        return ResponseEntity.created(new URI("/api/feed-backs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /feed-backs : Updates an existing feedBack.
     *
     * @param feedBackDTO the feedBackDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated feedBackDTO,
     * or with status 400 (Bad Request) if the feedBackDTO is not valid,
     * or with status 500 (Internal Server Error) if the feedBackDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/feed-backs")
    @Timed
    public ResponseEntity<FeedBackDTO> updateFeedBack(@RequestBody FeedBackDTO feedBackDTO) throws URISyntaxException {
        log.debug("REST request to update FeedBack : {}", feedBackDTO);
        if (feedBackDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }        
        FeedBackDTO result = feedBackService.save(feedBackDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, feedBackDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /feed-backs : get all the feedBacks.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of feedBacks in body
     */
    @GetMapping("/feed-backs")
    @Timed
    public ResponseEntity<List<FeedBackDTO>> getAllFeedBacks(Pageable pageable) {
        log.debug("REST request to get a page of FeedBacks");
        Page<FeedBackDTO> page = feedBackService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/feed-backs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /feed-backs/:id : get the "id" feedBack.
     *
     * @param id the id of the feedBackDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the feedBackDTO, or with status 404 (Not Found)
     */
    @GetMapping("/feed-backs/{id}")
    @Timed
    public ResponseEntity<FeedBackDTO> getFeedBack(@PathVariable Long id) {
        log.debug("REST request to get FeedBack : {}", id);
        Optional<FeedBackDTO> feedBackDTO = feedBackService.findOne(id);
        return ResponseUtil.wrapOrNotFound(feedBackDTO);
    }

    /**
     * DELETE  /feed-backs/:id : delete the "id" feedBack.
     *
     * @param id the id of the feedBackDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/feed-backs/{id}")
    @Timed
    public ResponseEntity<Void> deleteFeedBack(@PathVariable Long id) {
        log.debug("REST request to delete FeedBack : {}", id);
        feedBackService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
