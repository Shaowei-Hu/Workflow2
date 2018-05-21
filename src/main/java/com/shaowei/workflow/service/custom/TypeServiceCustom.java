package com.shaowei.workflow.service.custom;

import com.shaowei.workflow.domain.Type;

/**
 * Service Interface for managing Type.
 */
public interface TypeServiceCustom {

    /**
    * Search for the Type corresponding from the INSEE code.
    *
    *  @param inseeCode the inseeCode of the Type
    *  
    *  @return the entity
    */
    Type searchByCode(String inseeCode);

    /**
    * Import the Types from a json file in folder system.
    *
    *  @param path the path of the Type json file
    *  
    *  @return the number of instances imported
    */
    int importTypeFromJsonFile(String path);
}
