package com.shaowei.workflow.service.custom;

import com.shaowei.workflow.domain.FeedBack;

/**
 * Service Interface for managing FeedBack.
 */
public interface FeedBackServiceCustom {

    /**
    * Search for the FeedBack corresponding from the INSEE code.
    *
    *  @param inseeCode the inseeCode of the FeedBack
    *  
    *  @return the entity
    */
    FeedBack searchByCode(String inseeCode);

    /**
    * Import the FeedBacks from a json file in folder system.
    *
    *  @param path the path of the FeedBack json file
    *  
    *  @return the number of instances imported
    */
    int importFeedBackFromJsonFile(String path);
}
