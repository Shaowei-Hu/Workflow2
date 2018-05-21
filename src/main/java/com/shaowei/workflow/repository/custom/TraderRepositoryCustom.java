package com.shaowei.workflow.repository.custom;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shaowei.workflow.domain.Trader;


/**
 * Spring Data Elasticsearch repository for the Trader entity.
 */
public interface TraderRepositoryCustom extends JpaRepository<Trader, Long> {
	Trader findByName(String name);
}
