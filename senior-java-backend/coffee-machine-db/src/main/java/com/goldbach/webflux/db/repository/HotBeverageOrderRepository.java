package com.goldbach.webflux.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goldbach.webflux.db.domain.HotBeverageOrder;

/**
 * @author Davide Listello
 */
public interface HotBeverageOrderRepository extends JpaRepository<HotBeverageOrder, Integer> {

}
