package com.shaowei.workflow.repository.custom;

import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import com.shaowei.workflow.domain.Region;


/**
 * Spring Data Elasticsearch repository for the Region entity.
 */
public interface RegionRepositoryCustom extends JpaRepository<Region, Long> {

}
