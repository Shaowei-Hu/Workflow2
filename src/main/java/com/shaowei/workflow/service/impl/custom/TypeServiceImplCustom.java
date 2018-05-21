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
import com.shaowei.workflow.domain.Type;
import com.shaowei.workflow.repository.TypeRepository;
import com.shaowei.workflow.repository.custom.TypeRepositoryCustom;
import com.shaowei.workflow.service.custom.TypeServiceCustom;

/**
 * Service Implementation for custom code of  Type.
 */
@Service
@Transactional
public class TypeServiceImplCustom implements TypeServiceCustom {

    private final Logger log = LoggerFactory.getLogger(TypeServiceImplCustom.class);
    private final TypeRepository typeRepository;

    private final TypeRepositoryCustom typeRepositoryCustom;

    public TypeServiceImplCustom(TypeRepository typeRepository,
    TypeRepositoryCustom typeRepositoryCustom) {
        this.typeRepository = typeRepository;
        this.typeRepositoryCustom = typeRepositoryCustom;
    }

    /**
    *  Get one Type by INSEE code.
    *
    *  @param inseeCode the inseeCode of the entity
    *  @return the entity
    */
    @Override
    @Transactional(readOnly = true)
    public Type searchByCode(String inseeCode) {
        log.debug("Request to get Type by insee code: {}", inseeCode);
        List<Type> list = null;
        if(list != null && list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    /**
    *  Import the Types from a json file in folder system.
    *
    *  @param path the path of the Type json file
    *  
    *  @return the number of instances imported
    */
    @Override
    public int importTypeFromJsonFile(String path) {
        ObjectMapper mapper = new ObjectMapper();
        int length = 0;
        try {
        // Convert JSON string from file to Object
            List<Type> types = mapper.readValue(new File(path), new TypeReference<List<Type>>(){});
            for(Type type: types){
                typeRepository.save(type);
            }
            length = types.size();
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
