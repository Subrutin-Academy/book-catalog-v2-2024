package com.subrutin.catalog.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorQueryDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2470908329098194720L;

	private Long bookId;
	
	private String authorName;

}
