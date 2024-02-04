package com.subrutin.catalog.service;

import java.util.List;

import com.subrutin.catalog.dto.BookCreateDTO;
import com.subrutin.catalog.dto.BookDetailDTO;
import com.subrutin.catalog.dto.BookUpdateRequestDTO;

public interface BookService {
	
	public BookDetailDTO findBookDetailById(Long bookId);
	
	public List<BookDetailDTO> findBookListDetail();
	
	public void createNewBook(BookCreateDTO dto);
	
	public void updateBook(Long bookId, BookUpdateRequestDTO dto);
	
}
