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
import com.shaowei.workflow.domain.Action;
import com.shaowei.workflow.repository.ActionRepository;
import com.shaowei.workflow.repository.custom.ActionRepositoryCustom;
import com.shaowei.workflow.service.custom.ActionServiceCustom;

/**
 * Service Implementation for custom code of  Action.
 */
@Service
@Transactional
public class ActionServiceImplCustom implements ActionServiceCustom {

    private final Logger log = LoggerFactory.getLogger(ActionServiceImplCustom.class);
    private final ActionRepository actionRepository;

    private final ActionRepositoryCustom actionRepositoryCustom;

    public ActionServiceImplCustom(ActionRepository actionRepository,
    ActionRepositoryCustom actionRepositoryCustom) {
        this.actionRepository = actionRepository;
        this.actionRepositoryCustom = actionRepositoryCustom;
    }

    /**
    *  Get one Action by INSEE code.
    *
    *  @param inseeCode the inseeCode of the entity
    *  @return the entity
    */
    @Override
    @Transactional(readOnly = true)
    public Action searchByCode(String inseeCode) {
        log.debug("Request to get Action by insee code: {}", inseeCode);
        List<Action> list = null;
        if(list != null && list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    /**
    *  Import the Actions from a json file in folder system.
    *
    *  @param path the path of the Action json file
    *  
    *  @return the number of instances imported
    */
    @Override
    public int importActionFromJsonFile(String path) {
        ObjectMapper mapper = new ObjectMapper();
        int length = 0;
        try {
        // Convert JSON string from file to Object
            List<Action> actions = mapper.readValue(new File(path), new TypeReference<List<Action>>(){});
            for(Action action: actions){
                actionRepository.save(action);
            }
            length = actions.size();
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
