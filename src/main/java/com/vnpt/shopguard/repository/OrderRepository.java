package com.vnpt.shopguard.repository;

import com.vnpt.shopguard.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
