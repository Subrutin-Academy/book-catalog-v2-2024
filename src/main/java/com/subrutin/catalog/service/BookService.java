package com.subrutin.catalog.service;

import com.subrutin.catalog.dto.BookDetailDTO;

public interface BookService {
	public BookDetailDTO findBookDetailById(Long bookId);

}
