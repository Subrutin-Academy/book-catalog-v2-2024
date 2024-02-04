package com.subrutin.catalog.service;

import com.subrutin.catalog.dto.PublisherCreateRequestDTO;
import com.subrutin.catalog.dto.PublisherUpdateRequestDTO;

public interface PublisherService {
	
	public void createPublisher(PublisherCreateRequestDTO dto);
	
	public void updatePublisher(String publisherId, PublisherUpdateRequestDTO dto);

}
