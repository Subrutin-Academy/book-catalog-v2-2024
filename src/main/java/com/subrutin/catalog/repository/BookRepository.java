package com.subrutin.catalog.repository;

import com.subrutin.catalog.domain.Book;

public interface BookRepository {
	public Book findBookById(Long id);

}
