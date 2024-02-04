package com.subrutin.catalog.web;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.subrutin.catalog.dto.BookCreateRequestDTO;
import com.subrutin.catalog.dto.BookDetailResponseDTO;
import com.subrutin.catalog.dto.BookListResponseDTO;
import com.subrutin.catalog.dto.BookUpdateRequestDTO;
import com.subrutin.catalog.dto.ResultPageResponseDTO;
import com.subrutin.catalog.service.BookService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
public class BookResource {
	
	private final BookService bookService;

	//nama yang salah /get-book/{bookId}
	@GetMapping("/v1/book/{bookId}")
	public ResponseEntity<BookDetailResponseDTO> findBookDetail(@PathVariable("bookId") String id) {
		StopWatch stopWatch = new StopWatch();
		log.info("start findBookDetail "+id);
		stopWatch.start();
		BookDetailResponseDTO result =  bookService.findBookDetailById(id);
		stopWatch.stop();
		log.info("finish findBookDetail. execution time = {}",stopWatch.getTotalTimeMillis());
		return ResponseEntity.ok(result);

	}
	
	//nama yang salah /save-book /create-book
	@PostMapping("/v1/book")
	public ResponseEntity<Void> createANewBook(@RequestBody BookCreateRequestDTO dto){
		bookService.createNewBook(dto);
		return ResponseEntity.created(URI.create("/book")).build();
	}
	
	
	//get boot list ->
	//1. judul buku
	//2. nama penerbit //table publisher
	//3. nama penulis //table author
	
	@GetMapping("/v2/book")
	public ResponseEntity<ResultPageResponseDTO<BookListResponseDTO>> findBookList(
			@RequestParam(name = "page", required = true, defaultValue = "0") Integer page, 
			@RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit, 
			@RequestParam(name = "sortBy", required = true, defaultValue = "title") String sortBy,
			@RequestParam(name = "direction",required = true, defaultValue = "asc") String direction,
			@RequestParam(name = "bookTitle",required = false, defaultValue = "") String bookTitle,
			@RequestParam(name = "publisherName",required = false, defaultValue = "") String publisherName,
			@RequestParam(name = "authorName",required = false, defaultValue = "") String authorName){
		return ResponseEntity.ok().body(bookService.findBookList(page, limit, sortBy, direction, publisherName, bookTitle, authorName));
		
	}
	
	
	@GetMapping("/v1/book")
	public ResponseEntity<List<BookDetailResponseDTO>> findBookList(){
		return ResponseEntity.ok().body(bookService.findBookListDetail());
		
	}
	
	//PUT /book
	@PutMapping("/v1/book/{bookId}")
	public ResponseEntity<Void> updateBook(@PathVariable("bookId") Long bookId, 
			@RequestBody BookUpdateRequestDTO dto){
		bookService.updateBook(bookId, dto);
		return ResponseEntity.ok().build();
	}
	
	//DELETE /book
	@DeleteMapping("/v1/book/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable("bookId") Long bookId){
		bookService.deleteBook(bookId);
		return ResponseEntity.ok().build();
	}
	
}