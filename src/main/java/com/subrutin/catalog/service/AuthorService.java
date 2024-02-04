package com.subrutin.catalog.service;

import java.util.List;

import com.subrutin.catalog.dto.AuthorCreateRequestDTO;
import com.subrutin.catalog.dto.AuthorResponseDTO;

public interface AuthorService {
	
	public AuthorResponseDTO findAuthorById(Long id);
	
	public void createNewAuthor(List<AuthorCreateRequestDTO> dto);

}
