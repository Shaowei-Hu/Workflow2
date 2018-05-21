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
import com.shaowei.workflow.domain.FeedBack;
import com.shaowei.workflow.service.custom.FeedBackServiceCustom;
import com.shaowei.workflow.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing City.
 */
@RestController
@RequestMapping("/api")
public class FeedBackResourceCustom {

    private final Logger log = LoggerFactory.getLogger(FeedBackResourceCustom.class);

    private final FeedBackServiceCustom feedBackServiceCustom;

    public FeedBackResourceCustom(FeedBackServiceCustom feedBackServiceCustom) {
        this.feedBackServiceCustom = feedBackServiceCustom;
    }
    
    /**
     * SEARCH  /_search/feedBack/byCode/{inseeCode} : search for the city by insee code
     * 
     *
     * @param inseeCode the insee code of the FeedBack search
     * @return the result of the search, the ResponseEntity with status 200 (OK) and with body the FeedBack, or with status 404 (Not Found)
     */
    @GetMapping("/_search/feed-back/byCode/{inseeCode}")
    @Timed
    public ResponseEntity<FeedBack> searchCitiesByCode(@PathVariable String inseeCode) {
        log.debug("REST request to search for a page of FeedBacks for inseeCode {}", inseeCode);
        FeedBack feedBack = feedBackServiceCustom.searchByCode(inseeCode);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(feedBack));
    }
    
    /**
     *  Import the FeedBacks from a json file in folder system.
     *
     *  @param path the path of the FeedBack json file
     *  
     *  @return the number of instances imported
     */
    @GetMapping("/import/feedBack/fromJson")
    @Timed
    public ResponseEntity<FeedBack> importCitiesFromJson(@RequestParam String path) {
        log.debug("REST request to import cities from json");
        int length = feedBackServiceCustom.importFeedBackFromJsonFile(path);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityCreationAlert("FeedBacks[]", "length: " + length)).build();
    }

}
