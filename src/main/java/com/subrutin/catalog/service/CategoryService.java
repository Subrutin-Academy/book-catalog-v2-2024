package com.subrutin.catalog.service;

import java.util.List;

import com.subrutin.catalog.domain.Category;
import com.subrutin.catalog.dto.CategoryCreateUpdateRequestDTO;
import com.subrutin.catalog.dto.CategoryListResponseDTO;
import com.subrutin.catalog.dto.ResultPageResponseDTO;

public interface CategoryService {
	
	public void createAndUpdateCategory(CategoryCreateUpdateRequestDTO dto);
	
	public ResultPageResponseDTO<CategoryListResponseDTO> findCategoryList(Integer pages, 
			Integer limit, String sortBy, String direction, String categoryName);
	
	public List<Category> findCategories(List<String> categoryCodeList);
	
	public List<CategoryListResponseDTO> constructDTO(List<Category> categories);
	
}
