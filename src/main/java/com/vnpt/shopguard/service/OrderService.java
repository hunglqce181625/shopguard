package com.vnpt.shopguard.service;

import com.vnpt.shopguard.entity.Order;
import java.util.List;

public interface OrderService {
    List<Order> findAll();
    Order findById(Integer id);
}
