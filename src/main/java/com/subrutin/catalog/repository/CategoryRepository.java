package com.subrutin.catalog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subrutin.catalog.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
	
	public Optional<Category> findByCode(String code);

}
