package com.shaowei.workflow.repository;

import com.shaowei.workflow.domain.Client;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.*;

/**
 * Spring Data JPA repository for the Client entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
