package com.shaowei.workflow.service.impl.custom;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shaowei.workflow.domain.Region;
import com.shaowei.workflow.repository.RegionRepository;
import com.shaowei.workflow.repository.custom.RegionRepositoryCustom;
import com.shaowei.workflow.service.custom.RegionServiceCustom;

/**
 * Service Implementation for custom code of  Region.
 */
@Service
@Transactional
public class RegionServiceImplCustom implements RegionServiceCustom {

    private final Logger log = LoggerFactory.getLogger(RegionServiceImplCustom.class);
    private final RegionRepository regionRepository;

    private final RegionRepositoryCustom regionRepositoryCustom;

    public RegionServiceImplCustom(RegionRepository regionRepository,
    RegionRepositoryCustom regionRepositoryCustom) {
        this.regionRepository = regionRepository;
        this.regionRepositoryCustom = regionRepositoryCustom;
    }

    /**
    *  Get one Region by INSEE code.
    *
    *  @param inseeCode the inseeCode of the entity
    *  @return the entity
    */
    @Override
    @Transactional(readOnly = true)
    public Region searchByCode(String inseeCode) {
        log.debug("Request to get Region by insee code: {}", inseeCode);
        List<Region> list = null;
        if(list != null && list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    /**
    *  Import the Regions from a json file in folder system.
    *
    *  @param path the path of the Region json file
    *  
    *  @return the number of instances imported
    */
    @Override
    public int importRegionFromJsonFile(String path) {
        ObjectMapper mapper = new ObjectMapper();
        int length = 0;
        try {
        // Convert JSON string from file to Object
            List<Region> regions = mapper.readValue(new File(path), new TypeReference<List<Region>>(){});
            for(Region region: regions){
                regionRepository.save(region);
            }
            length = regions.size();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return length;
    }
}
