package com.shaowei.workflow.service.mapper;

import com.shaowei.workflow.domain.*;
import com.shaowei.workflow.service.dto.TraderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Trader and its DTO TraderDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TraderMapper extends EntityMapper<TraderDTO, Trader> {


    @Mapping(target = "teams", ignore = true)
    Trader toEntity(TraderDTO traderDTO);

    default Trader fromId(Long id) {
        if (id == null) {
            return null;
        }
        Trader trader = new Trader();
        trader.setId(id);
        return trader;
    }
}
