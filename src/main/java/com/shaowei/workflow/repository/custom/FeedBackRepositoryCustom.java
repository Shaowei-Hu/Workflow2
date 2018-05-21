package com.shaowei.workflow.repository.custom;

import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import com.shaowei.workflow.domain.FeedBack;


/**
 * Spring Data Elasticsearch repository for the FeedBack entity.
 */
public interface FeedBackRepositoryCustom extends JpaRepository<FeedBack, Long> {

}
