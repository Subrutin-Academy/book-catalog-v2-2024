package com.subrutin.catalog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.subrutin.catalog.domain.Category;
import com.subrutin.catalog.dto.CategoryQueryDTO;

public interface CategoryRepository extends JpaRepository<Category, String> {
	
	public Optional<Category> findByCode(String code);
	
	public Page<Category> findByNameLikeIgnoreCase(String categoryName, Pageable pageable);
	
	public List<Category> findByCodeIn(List<String> codes);
	
	@Query("SELECT new com.subrutin.catalog.dto.CategoryQueryDTO(b.id, bc.code) "
			+ "FROM Book b "
			+ "JOIN b.categories bc "
			+ "WHERE b.id IN :bookIdList")
	public List<CategoryQueryDTO> findCategoryByBookIdList(List<Long> bookIdList);

}
