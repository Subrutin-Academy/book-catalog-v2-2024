package com.subrutin.catalog.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.subrutin.catalog.dto.AuthorResponseDTO;
import com.subrutin.catalog.service.AuthorService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class AuthorResource {
	
	private final AuthorService authorService;
	
	//author detail
	@GetMapping("/author/{id}/detail")
	public ResponseEntity<AuthorResponseDTO> findAuthorById(@PathVariable Long id){
		return ResponseEntity.ok().body(authorService.findAuthorById(id));
	}

}
