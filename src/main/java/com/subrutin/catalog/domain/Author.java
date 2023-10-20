package com.subrutin.catalog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
	
	private Long id;
	
	private String name;
	
	private Long birthDate;
	
	
	

}