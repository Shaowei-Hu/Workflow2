package com.shaowei.workflow.service.mapper;

import com.shaowei.workflow.domain.*;
import com.shaowei.workflow.service.dto.TypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Type and its DTO TypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypeMapper extends EntityMapper<TypeDTO, Type> {



    default Type fromId(Long id) {
        if (id == null) {
            return null;
        }
        Type type = new Type();
        type.setId(id);
        return type;
    }
}
