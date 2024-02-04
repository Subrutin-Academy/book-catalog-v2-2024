package com.subrutin.catalog.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PublisherCreateRequestDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6264675048939616940L;

	@NotBlank
	private String publisherName;
	
	@NotBlank
	private String companyName;
	
	private String address;

}
