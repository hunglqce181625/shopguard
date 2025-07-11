package com.vnpt.shopguard.service;

import com.vnpt.shopguard.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findById(Integer id);

    void save(Category category);

    boolean deleteById(Integer id);

    List<Category> searchByKeyword(String keyword);
}
