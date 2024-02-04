package com.subrutin.catalog.service;

import com.subrutin.catalog.dto.CategoryCreateUpdateRequestDTO;

public interface CategoryService {
	
	public void createAndUpdateCategory(CategoryCreateUpdateRequestDTO dto);
	
}
