package com.vnpt.shopguard.service;

import com.vnpt.shopguard.entity.User;
import java.util.List;

public interface AdminUserService {
    List<User> findAll();
    User findById(Integer id);
}
