package com.subrutin.catalog.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.subrutin.catalog.dto.BookDetailDTO;
import com.subrutin.catalog.service.BookService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class BookResource {

	private final BookService bookService;
	
	@GetMapping("/book/{bookId}")
	public BookDetailDTO findBookDetail(@PathVariable("bookId") Long id) {
		return bookService.findBookDetailById(id);
	}
}