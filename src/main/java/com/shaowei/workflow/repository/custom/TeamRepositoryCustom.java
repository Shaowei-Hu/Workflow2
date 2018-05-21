package com.shaowei.workflow.repository.custom;

import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import com.shaowei.workflow.domain.Team;


/**
 * Spring Data Elasticsearch repository for the Team entity.
 */
public interface TeamRepositoryCustom extends JpaRepository<Team, Long> {

}
