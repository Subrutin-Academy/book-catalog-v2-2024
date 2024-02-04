package com.subrutin.catalog.service.impl;

import org.springframework.stereotype.Service;

import com.subrutin.catalog.domain.Category;
import com.subrutin.catalog.dto.CategoryCreateUpdateRequestDTO;
import com.subrutin.catalog.repository.CategoryRepository;
import com.subrutin.catalog.service.CategoryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryRepository categoryRepository;

	@Override
	public void createAndUpdateCategory(CategoryCreateUpdateRequestDTO dto) {
		 Category category =  categoryRepository.findByCode(dto.getCode().toLowerCase()).orElse(new Category());
		 if(category.getCode()==null) {
			 category.setCode(dto.getCode().toLowerCase()); //new 
		 }
		 category.setName(dto.getName());
		 category.setDescription(dto.getDescription());
		 
		 categoryRepository.save(category);
	}

}
