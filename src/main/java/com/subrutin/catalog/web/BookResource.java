package com.subrutin.catalog.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.subrutin.catalog.dto.BookDetailDTO;
import com.subrutin.catalog.service.BookService;
import com.subrutin.catalog.service.impl.GreetingServiceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
public class BookResource {
	
	private final BookService bookService;



	@GetMapping("/book/{bookId}")
	public BookDetailDTO findBookDetail(@PathVariable("bookId") Long id) {
		StopWatch stopWatch = new StopWatch();
		log.info("start findBookDetail "+id);
		BookDetailDTO result =  bookService.findBookDetailById(id);
		log.info("finish findBookDetail. execution time = {}",stopWatch);
		return result;

	}
}