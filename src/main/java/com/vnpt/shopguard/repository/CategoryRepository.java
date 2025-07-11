package com.vnpt.shopguard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vnpt.shopguard.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	List<Category> findByNameContainingIgnoreCase(String keyword);
}
