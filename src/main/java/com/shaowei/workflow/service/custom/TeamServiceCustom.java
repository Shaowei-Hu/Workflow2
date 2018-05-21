package com.shaowei.workflow.service.custom;

import com.shaowei.workflow.domain.Team;

/**
 * Service Interface for managing Team.
 */
public interface TeamServiceCustom {

    /**
    * Search for the Team corresponding from the INSEE code.
    *
    *  @param inseeCode the inseeCode of the Team
    *  
    *  @return the entity
    */
    Team searchByCode(String inseeCode);

    /**
    * Import the Teams from a json file in folder system.
    *
    *  @param path the path of the Team json file
    *  
    *  @return the number of instances imported
    */
    int importTeamFromJsonFile(String path);
}
