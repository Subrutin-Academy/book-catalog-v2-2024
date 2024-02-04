package com.subrutin.catalog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.subrutin.catalog.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
	
	public Optional<Category> findByCode(String code);
	
	public Page<Category> findByNameLikeIgnoreCase(String categoryName, Pageable pageable);
	
	public List<Category> findByCodeIn(List<String> codes);

}
