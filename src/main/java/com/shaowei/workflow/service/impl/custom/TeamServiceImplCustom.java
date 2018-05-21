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
import com.shaowei.workflow.domain.Team;
import com.shaowei.workflow.repository.TeamRepository;
import com.shaowei.workflow.repository.custom.TeamRepositoryCustom;
import com.shaowei.workflow.service.custom.TeamServiceCustom;

/**
 * Service Implementation for custom code of  Team.
 */
@Service
@Transactional
public class TeamServiceImplCustom implements TeamServiceCustom {

    private final Logger log = LoggerFactory.getLogger(TeamServiceImplCustom.class);
    private final TeamRepository teamRepository;

    private final TeamRepositoryCustom teamRepositoryCustom;

    public TeamServiceImplCustom(TeamRepository teamRepository,
    TeamRepositoryCustom teamRepositoryCustom) {
        this.teamRepository = teamRepository;
        this.teamRepositoryCustom = teamRepositoryCustom;
    }

    /**
    *  Get one Team by INSEE code.
    *
    *  @param inseeCode the inseeCode of the entity
    *  @return the entity
    */
    @Override
    @Transactional(readOnly = true)
    public Team searchByCode(String inseeCode) {
        log.debug("Request to get Team by insee code: {}", inseeCode);
        List<Team> list = null;
        if(list != null && list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    /**
    *  Import the Teams from a json file in folder system.
    *
    *  @param path the path of the Team json file
    *  
    *  @return the number of instances imported
    */
    @Override
    public int importTeamFromJsonFile(String path) {
        ObjectMapper mapper = new ObjectMapper();
        int length = 0;
        try {
        // Convert JSON string from file to Object
            List<Team> teams = mapper.readValue(new File(path), new TypeReference<List<Team>>(){});
            for(Team team: teams){
                teamRepository.save(team);
            }
            length = teams.size();
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
