package com.subrutin.catalog.service;

import com.subrutin.catalog.dto.AuthorResponseDTO;

public interface AuthorService {
	
	public AuthorResponseDTO findAuthorById(Long id);

}
