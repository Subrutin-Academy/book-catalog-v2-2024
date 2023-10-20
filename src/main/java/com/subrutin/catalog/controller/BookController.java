package com.subrutin.catalog.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.subrutin.catalog.dto.BookCreateDTO;
import com.subrutin.catalog.dto.BookDetailDTO;
import com.subrutin.catalog.service.BookService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping("/book")
public class BookController {

	private final BookService bookService;

	@GetMapping("/list")
	public String findBookList(Model model) {
		List<BookDetailDTO> books = bookService.findBookListDetail();
		model.addAttribute("books", books);
		return "book/list";
	}

	@GetMapping("/new")
	public String loadBookForm(Model model) {
		BookCreateDTO dto = new BookCreateDTO();
		model.addAttribute("bookCreateDTO", dto);
		return "book/book-new";
	}

	@PostMapping("/new")
	public String addNewBook(@ModelAttribute("bookCreateDTO") BookCreateDTO dto, Model model) {
		bookService.createNewBook(dto);
		return "redirect:/book/list";

	}

}
