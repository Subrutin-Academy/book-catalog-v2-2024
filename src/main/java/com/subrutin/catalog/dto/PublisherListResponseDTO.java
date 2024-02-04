package com.subrutin.catalog.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PublisherListResponseDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8060672199662256692L;

	private String publisherId;
	
	private String publisherName;
	
	private String companyName;

}
