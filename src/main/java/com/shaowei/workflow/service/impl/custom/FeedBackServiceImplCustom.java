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
import com.shaowei.workflow.domain.FeedBack;
import com.shaowei.workflow.repository.FeedBackRepository;
import com.shaowei.workflow.repository.custom.FeedBackRepositoryCustom;
import com.shaowei.workflow.service.custom.FeedBackServiceCustom;

/**
 * Service Implementation for custom code of  FeedBack.
 */
@Service
@Transactional
public class FeedBackServiceImplCustom implements FeedBackServiceCustom {

    private final Logger log = LoggerFactory.getLogger(FeedBackServiceImplCustom.class);
    private final FeedBackRepository feedBackRepository;

    private final FeedBackRepositoryCustom feedBackRepositoryCustom;

    public FeedBackServiceImplCustom(FeedBackRepository feedBackRepository,
    FeedBackRepositoryCustom feedBackRepositoryCustom) {
        this.feedBackRepository = feedBackRepository;
        this.feedBackRepositoryCustom = feedBackRepositoryCustom;
    }

    /**
    *  Get one FeedBack by INSEE code.
    *
    *  @param inseeCode the inseeCode of the entity
    *  @return the entity
    */
    @Override
    @Transactional(readOnly = true)
    public FeedBack searchByCode(String inseeCode) {
        log.debug("Request to get FeedBack by insee code: {}", inseeCode);
        List<FeedBack> list = null;
        if(list != null && list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    /**
    *  Import the FeedBacks from a json file in folder system.
    *
    *  @param path the path of the FeedBack json file
    *  
    *  @return the number of instances imported
    */
    @Override
    public int importFeedBackFromJsonFile(String path) {
        ObjectMapper mapper = new ObjectMapper();
        int length = 0;
        try {
        // Convert JSON string from file to Object
            List<FeedBack> feedBacks = mapper.readValue(new File(path), new TypeReference<List<FeedBack>>(){});
            for(FeedBack feedBack: feedBacks){
                feedBackRepository.save(feedBack);
            }
            length = feedBacks.size();
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
