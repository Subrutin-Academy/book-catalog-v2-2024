package com.subrutin.catalog.service;

import java.util.List;

import com.subrutin.catalog.dto.BookCreateRequestDTO;
import com.subrutin.catalog.dto.BookDetailDTO;
import com.subrutin.catalog.dto.BookUpdateRequestDTO;

public interface BookService {
	
	public BookDetailDTO findBookDetailById(Long bookId);
	
	public List<BookDetailDTO> findBookListDetail();
	
	public void createNewBook(BookCreateRequestDTO dto);
	
	public void updateBook(Long bookId, BookUpdateRequestDTO dto);
	
	public void deleteBook(Long bookId);
	
}
