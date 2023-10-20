package com.subrutin.catalog.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class BookDetailDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7889938648939242355L;

	private Long bookId;
	
	private String authorName;
	
	private String bookTitle;
	
	private String bookDescription;

	
	

}
