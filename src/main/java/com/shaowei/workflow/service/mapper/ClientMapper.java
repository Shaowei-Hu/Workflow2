package com.shaowei.workflow.service.mapper;

import com.shaowei.workflow.domain.*;
import com.shaowei.workflow.service.dto.ClientDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Client and its DTO ClientDTO.
 */
@Mapper(componentModel = "spring", uses = {TraderMapper.class, RegionMapper.class, TypeMapper.class})
public interface ClientMapper extends EntityMapper<ClientDTO, Client> {

    @Mapping(source = "trader.id", target = "traderId")
    @Mapping(source = "region.id", target = "regionId")
    @Mapping(source = "region.name", target = "regionName")
    @Mapping(source = "type.id", target = "typeId")
    @Mapping(source = "type.name", target = "typeName")
    ClientDTO toDto(Client client);

    @Mapping(source = "traderId", target = "trader")
    @Mapping(source = "regionId", target = "region")
    @Mapping(source = "typeId", target = "type")
    Client toEntity(ClientDTO clientDTO);

    default Client fromId(Long id) {
        if (id == null) {
            return null;
        }
        Client client = new Client();
        client.setId(id);
        return client;
    }
}
