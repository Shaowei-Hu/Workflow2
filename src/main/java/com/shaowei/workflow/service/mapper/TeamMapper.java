package com.shaowei.workflow.service.mapper;

import com.shaowei.workflow.domain.*;
import com.shaowei.workflow.service.dto.TeamDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Team and its DTO TeamDTO.
 */
@Mapper(componentModel = "spring", uses = {TraderMapper.class})
public interface TeamMapper extends EntityMapper<TeamDTO, Team> {



    default Team fromId(Long id) {
        if (id == null) {
            return null;
        }
        Team team = new Team();
        team.setId(id);
        return team;
    }
}
