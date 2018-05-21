package com.shaowei.workflow.service.custom;

import com.shaowei.workflow.domain.Action;

/**
 * Service Interface for managing Action.
 */
public interface ActionServiceCustom {

    /**
    * Search for the Action corresponding from the INSEE code.
    *
    *  @param inseeCode the inseeCode of the Action
    *  
    *  @return the entity
    */
    Action searchByCode(String inseeCode);

    /**
    * Import the Actions from a json file in folder system.
    *
    *  @param path the path of the Action json file
    *  
    *  @return the number of instances imported
    */
    int importActionFromJsonFile(String path);
}
