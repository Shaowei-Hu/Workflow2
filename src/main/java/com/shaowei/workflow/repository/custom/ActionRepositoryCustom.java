package com.shaowei.workflow.repository.custom;

import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import com.shaowei.workflow.domain.Action;


/**
 * Spring Data Elasticsearch repository for the Action entity.
 */
public interface ActionRepositoryCustom extends JpaRepository<Action, Long> {

}
