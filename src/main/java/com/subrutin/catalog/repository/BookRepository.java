package com.subrutin.catalog.repository;

import java.util.List;

import com.subrutin.catalog.domain.Book;

public interface BookRepository {
	public Book findBookById(Long id);
	
	public List<Book> findAll();
	
	public void save(Book book);
	
	public void update(Book book);
	
	public void delete(Long bookId);


}
