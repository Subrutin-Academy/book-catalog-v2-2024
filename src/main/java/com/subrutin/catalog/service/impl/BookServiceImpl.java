package com.subrutin.catalog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.subrutin.catalog.domain.Author;
import com.subrutin.catalog.domain.Book;
import com.subrutin.catalog.dto.BookCreateDTO;
import com.subrutin.catalog.dto.BookDetailDTO;
import com.subrutin.catalog.dto.BookUpdateRequestDTO;
import com.subrutin.catalog.exception.BadRequestException;
import com.subrutin.catalog.repository.BookRepository;
import com.subrutin.catalog.service.BookService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service("bookService")
public class BookServiceImpl implements BookService{
	

	private final BookRepository bookRepository;


	@Override
	public BookDetailDTO findBookDetailById(Long bookId) {
		Book book = bookRepository.findById(bookId)
				.orElseThrow(()-> new BadRequestException("book_id.invalid"));
		BookDetailDTO dto = new BookDetailDTO();
		dto.setBookId(book.getId());
//		dto.setAuthorName(book.getAuthor().getName());
		dto.setBookTitle(book.getTitle());
		dto.setBookDescription(book.getDescription());
		return dto;
	}


	@Override
	public List<BookDetailDTO> findBookListDetail() {
		List<Book> books = bookRepository.findAll();
		return books.stream().map((b)->{
			BookDetailDTO dto = new BookDetailDTO();
//			dto.setAuthorName(b.getAuthor().getName());
			dto.setBookDescription(b.getDescription());
			dto.setBookId(b.getId());
			dto.setBookTitle(b.getTitle());
			return dto;
		}).collect(Collectors.toList());
	}


	@Override
	public void createNewBook(BookCreateDTO dto) {
		Author author = new Author();
		author.setName(dto.getAuthorName());
	
		Book book = new Book();
//		book.setAuthor(author);
		book.setTitle(dto.getBookTitle());
		book.setDescription(dto.getDescription());
		bookRepository.save(book);
		
	}


	@Override
	public void updateBook(Long bookId, BookUpdateRequestDTO dto) {
		//get book from repository
		Book book = bookRepository.findById(bookId)
				.orElseThrow(()-> new BadRequestException("book_id.invalid"));
		//update
		book.setTitle(dto.getBookTitle());
		book.setDescription(dto.getDescription());
		//save
		bookRepository.save(book);
		
	}


	@Override
	public void deleteBook(Long bookId) {
		bookRepository.deleteById(bookId);
		
	}

//	public BookRepository getBookRepository() {
//		return bookRepository;
//	}
//
//	public void setBookRepository(BookRepository bookRepository) {
//		this.bookRepository = bookRepository;
//	}
	
	
	
	
}