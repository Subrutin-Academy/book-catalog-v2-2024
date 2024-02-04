package com.subrutin.catalog.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PublisherUpdateRequestDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7699115302294881826L;

	private String publisherName;
	
	private String companyName;
	
	private String address;
}
