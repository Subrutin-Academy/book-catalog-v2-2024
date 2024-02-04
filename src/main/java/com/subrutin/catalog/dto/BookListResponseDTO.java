package com.subrutin.catalog.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookListResponseDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4836684745260708430L;

	private String id;
	
	private String title;
	
	private String description;
	
	private String publisherName;
	
	private List<String> categoryCodes;
	
	private List<String> authorNames;

}
