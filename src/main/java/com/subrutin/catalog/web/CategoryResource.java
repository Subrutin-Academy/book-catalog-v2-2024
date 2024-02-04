package com.subrutin.catalog.web;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.subrutin.catalog.dto.CategoryCreateUpdateRequestDTO;
import com.subrutin.catalog.dto.CategoryListResponseDTO;
import com.subrutin.catalog.dto.ResultPageResponseDTO;
import com.subrutin.catalog.service.CategoryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class CategoryResource {

	private final CategoryService categoryService;
	
	@PostMapping("/v1/category")
	public ResponseEntity<Void> createAndUpdateCategory(@RequestBody CategoryCreateUpdateRequestDTO dto){
		categoryService.createAndUpdateCategory(dto);
		return ResponseEntity.created(URI.create("/v1/category")).build();
		
	}
	
	@GetMapping("/v1/category")
	public ResponseEntity<ResultPageResponseDTO<CategoryListResponseDTO>> findCategoryList(
			@RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages, 
			@RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
			@RequestParam(name="sortBy", required = true, defaultValue = "name") String sortBy,
			@RequestParam(name="direction", required = true, defaultValue = "asc") String direction,
			@RequestParam(name="categoryName", required = false) String categoryName){
		return ResponseEntity.ok().body(categoryService.findCategoryList(pages, limit, sortBy, direction, categoryName));
	}
}
