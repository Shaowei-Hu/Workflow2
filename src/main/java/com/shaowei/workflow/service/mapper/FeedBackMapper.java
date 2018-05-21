package com.shaowei.workflow.service.mapper;

import com.shaowei.workflow.domain.*;
import com.shaowei.workflow.service.dto.FeedBackDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity FeedBack and its DTO FeedBackDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FeedBackMapper extends EntityMapper<FeedBackDTO, FeedBack> {



    default FeedBack fromId(Long id) {
        if (id == null) {
            return null;
        }
        FeedBack feedBack = new FeedBack();
        feedBack.setId(id);
        return feedBack;
    }
}
