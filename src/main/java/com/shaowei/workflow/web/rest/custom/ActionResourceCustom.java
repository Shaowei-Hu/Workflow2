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
import com.shaowei.workflow.domain.Action;
import com.shaowei.workflow.service.custom.ActionServiceCustom;
import com.shaowei.workflow.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing City.
 */
@RestController
@RequestMapping("/api")
public class ActionResourceCustom {

    private final Logger log = LoggerFactory.getLogger(ActionResourceCustom.class);

    private final ActionServiceCustom actionServiceCustom;

    public ActionResourceCustom(ActionServiceCustom actionServiceCustom) {
        this.actionServiceCustom = actionServiceCustom;
    }
    
    /**
     * SEARCH  /_search/action/byCode/{inseeCode} : search for the city by insee code
     * 
     *
     * @param inseeCode the insee code of the Action search
     * @return the result of the search, the ResponseEntity with status 200 (OK) and with body the Action, or with status 404 (Not Found)
     */
    @GetMapping("/_search/action/byCode/{inseeCode}")
    @Timed
    public ResponseEntity<Action> searchCitiesByCode(@PathVariable String inseeCode) {
        log.debug("REST request to search for a page of Actions for inseeCode {}", inseeCode);
        Action action = actionServiceCustom.searchByCode(inseeCode);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(action));
    }
    
    /**
     *  Import the Actions from a json file in folder system.
     *
     *  @param path the path of the Action json file
     *  
     *  @return the number of instances imported
     */
    @GetMapping("/import/action/fromJson")
    @Timed
    public ResponseEntity<Action> importCitiesFromJson(@RequestParam String path) {
        log.debug("REST request to import cities from json");
        int length = actionServiceCustom.importActionFromJsonFile(path);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityCreationAlert("Actions[]", "length: " + length)).build();
    }

}
