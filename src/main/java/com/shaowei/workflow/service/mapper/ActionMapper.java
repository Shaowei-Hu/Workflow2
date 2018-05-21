package com.shaowei.workflow.service.mapper;

import com.shaowei.workflow.domain.*;
import com.shaowei.workflow.service.dto.ActionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Action and its DTO ActionDTO.
 */
@Mapper(componentModel = "spring", uses = {FeedBackMapper.class, ClientMapper.class})
public interface ActionMapper extends EntityMapper<ActionDTO, Action> {

    @Mapping(source = "feedBack.id", target = "feedBackId")
    @Mapping(source = "client.id", target = "clientId")
    ActionDTO toDto(Action action);

    @Mapping(source = "feedBackId", target = "feedBack")
    @Mapping(source = "clientId", target = "client")
    Action toEntity(ActionDTO actionDTO);

    default Action fromId(Long id) {
        if (id == null) {
            return null;
        }
        Action action = new Action();
        action.setId(id);
        return action;
    }
}
