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
import com.shaowei.workflow.domain.Type;
import com.shaowei.workflow.service.custom.TypeServiceCustom;
import com.shaowei.workflow.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing City.
 */
@RestController
@RequestMapping("/api")
public class TypeResourceCustom {

    private final Logger log = LoggerFactory.getLogger(TypeResourceCustom.class);

    private final TypeServiceCustom typeServiceCustom;

    public TypeResourceCustom(TypeServiceCustom typeServiceCustom) {
        this.typeServiceCustom = typeServiceCustom;
    }
    
    /**
     * SEARCH  /_search/type/byCode/{inseeCode} : search for the city by insee code
     * 
     *
     * @param inseeCode the insee code of the Type search
     * @return the result of the search, the ResponseEntity with status 200 (OK) and with body the Type, or with status 404 (Not Found)
     */
    @GetMapping("/_search/type/byCode/{inseeCode}")
    @Timed
    public ResponseEntity<Type> searchCitiesByCode(@PathVariable String inseeCode) {
        log.debug("REST request to search for a page of Types for inseeCode {}", inseeCode);
        Type type = typeServiceCustom.searchByCode(inseeCode);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(type));
    }
    
    /**
     *  Import the Types from a json file in folder system.
     *
     *  @param path the path of the Type json file
     *  
     *  @return the number of instances imported
     */
    @GetMapping("/import/type/fromJson")
    @Timed
    public ResponseEntity<Type> importCitiesFromJson(@RequestParam String path) {
        log.debug("REST request to import cities from json");
        int length = typeServiceCustom.importTypeFromJsonFile(path);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityCreationAlert("Types[]", "length: " + length)).build();
    }

}
