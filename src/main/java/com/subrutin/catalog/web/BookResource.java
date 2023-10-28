package com.subrutin.catalog.web;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.subrutin.catalog.dto.BookCreateDTO;
import com.subrutin.catalog.dto.BookDetailDTO;
import com.subrutin.catalog.service.BookService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
public class BookResource {
	
	private final BookService bookService;

	//nama yang salah /get-book/{bookId}
	@GetMapping("/book/{bookId}")
	public BookDetailDTO findBookDetail(@PathVariable("bookId") Long id) {
		StopWatch stopWatch = new StopWatch();
		log.info("start findBookDetail "+id);
		stopWatch.start();
		BookDetailDTO result =  bookService.findBookDetailById(id);
		log.info("finish findBookDetail. execution time = {}",stopWatch.getTotalTimeMillis());
		return result;

	}
	
	//nama yang salah /save-book /create-book
	@PostMapping("/book")
	public ResponseEntity<Void> createANewBook(@RequestBody BookCreateDTO dto){
		bookService.createNewBook(dto);
		return ResponseEntity.created(URI.create("/book")).build();
	}
	
	@GetMapping("/book")
	public ResponseEntity<List<BookDetailDTO>> findBookList(){
		return ResponseEntity.ok().body(bookService.findBookListDetail());
		
	}
	
	//PUT /book
	
	//DELETE /book
	
}