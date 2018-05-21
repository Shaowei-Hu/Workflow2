package com.shaowei.workflow.service.custom;

import com.shaowei.workflow.domain.Region;

/**
 * Service Interface for managing Region.
 */
public interface RegionServiceCustom {

    /**
    * Search for the Region corresponding from the INSEE code.
    *
    *  @param inseeCode the inseeCode of the Region
    *  
    *  @return the entity
    */
    Region searchByCode(String inseeCode);

    /**
    * Import the Regions from a json file in folder system.
    *
    *  @param path the path of the Region json file
    *  
    *  @return the number of instances imported
    */
    int importRegionFromJsonFile(String path);
}
