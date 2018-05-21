package com.shaowei.workflow.repository.custom;

import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import com.shaowei.workflow.domain.Type;


/**
 * Spring Data Elasticsearch repository for the Type entity.
 */
public interface TypeRepositoryCustom extends JpaRepository<Type, Long> {

}
