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
import com.shaowei.workflow.domain.Team;
import com.shaowei.workflow.service.custom.TeamServiceCustom;
import com.shaowei.workflow.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing City.
 */
@RestController
@RequestMapping("/api")
public class TeamResourceCustom {

    private final Logger log = LoggerFactory.getLogger(TeamResourceCustom.class);

    private final TeamServiceCustom teamServiceCustom;

    public TeamResourceCustom(TeamServiceCustom teamServiceCustom) {
        this.teamServiceCustom = teamServiceCustom;
    }
    
    /**
     * SEARCH  /_search/team/byCode/{inseeCode} : search for the city by insee code
     * 
     *
     * @param inseeCode the insee code of the Team search
     * @return the result of the search, the ResponseEntity with status 200 (OK) and with body the Team, or with status 404 (Not Found)
     */
    @GetMapping("/_search/team/byCode/{inseeCode}")
    @Timed
    public ResponseEntity<Team> searchCitiesByCode(@PathVariable String inseeCode) {
        log.debug("REST request to search for a page of Teams for inseeCode {}", inseeCode);
        Team team = teamServiceCustom.searchByCode(inseeCode);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(team));
    }
    
    /**
     *  Import the Teams from a json file in folder system.
     *
     *  @param path the path of the Team json file
     *  
     *  @return the number of instances imported
     */
    @GetMapping("/import/team/fromJson")
    @Timed
    public ResponseEntity<Team> importCitiesFromJson(@RequestParam String path) {
        log.debug("REST request to import cities from json");
        int length = teamServiceCustom.importTeamFromJsonFile(path);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityCreationAlert("Teams[]", "length: " + length)).build();
    }

}
