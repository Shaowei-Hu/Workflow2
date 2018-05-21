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
import com.shaowei.workflow.domain.Region;
import com.shaowei.workflow.service.custom.RegionServiceCustom;
import com.shaowei.workflow.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing City.
 */
@RestController
@RequestMapping("/api")
public class RegionResourceCustom {

    private final Logger log = LoggerFactory.getLogger(RegionResourceCustom.class);

    private final RegionServiceCustom regionServiceCustom;

    public RegionResourceCustom(RegionServiceCustom regionServiceCustom) {
        this.regionServiceCustom = regionServiceCustom;
    }
    
    /**
     * SEARCH  /_search/region/byCode/{inseeCode} : search for the city by insee code
     * 
     *
     * @param inseeCode the insee code of the Region search
     * @return the result of the search, the ResponseEntity with status 200 (OK) and with body the Region, or with status 404 (Not Found)
     */
    @GetMapping("/_search/region/byCode/{inseeCode}")
    @Timed
    public ResponseEntity<Region> searchCitiesByCode(@PathVariable String inseeCode) {
        log.debug("REST request to search for a page of Regions for inseeCode {}", inseeCode);
        Region region = regionServiceCustom.searchByCode(inseeCode);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(region));
    }
    
    /**
     *  Import the Regions from a json file in folder system.
     *
     *  @param path the path of the Region json file
     *  
     *  @return the number of instances imported
     */
    @GetMapping("/import/region/fromJson")
    @Timed
    public ResponseEntity<Region> importCitiesFromJson(@RequestParam String path) {
        log.debug("REST request to import cities from json");
        int length = regionServiceCustom.importRegionFromJsonFile(path);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityCreationAlert("Regions[]", "length: " + length)).build();
    }

}
