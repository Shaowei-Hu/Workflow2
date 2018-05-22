package com.shaowei.workflow.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shaowei.workflow.domain.Client;
import com.shaowei.workflow.domain.Trader;


/**
 * Spring Data Elasticsearch repository for the Client entity.
 */
public interface ClientRepositoryCustom extends JpaRepository<Client, Long> {
    Page<Client> findByTrader(Trader trader, Pageable pageable);
    Page<Client> findByTraderNotAndTrader_Teams_Traders(Trader trader, Trader current, Pageable pageable);
}
