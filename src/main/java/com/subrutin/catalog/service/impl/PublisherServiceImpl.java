package com.subrutin.catalog.service.impl;

import org.springframework.stereotype.Service;

import com.subrutin.catalog.domain.Publisher;
import com.subrutin.catalog.dto.PublisherCreateRequestDTO;
import com.subrutin.catalog.dto.PublisherUpdateRequestDTO;
import com.subrutin.catalog.exception.BadRequestException;
import com.subrutin.catalog.repository.PublisherRepository;
import com.subrutin.catalog.service.PublisherService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

	private final PublisherRepository publisherRepository;
	@Override
	public void createPublisher(PublisherCreateRequestDTO dto) {
		Publisher publisher = new Publisher();
		publisher.setName(dto.getPublisherName());
		publisher.setCompanyName(dto.getCompanyName());
		publisher.setAddress(dto.getAddress());
		
		publisherRepository.save(publisher);
		
	}

	@Override
	public void updatePublisher(String publisherId, PublisherUpdateRequestDTO dto) {
		Publisher publisher = publisherRepository.findBySecureId(publisherId)
		.orElseThrow(()-> new BadRequestException("invalid.publisher_id"));
		publisher.setName(dto.getPublisherName()==null|| dto.getPublisherName().isBlank()?publisher.getName():dto.getPublisherName());
		publisher.setCompanyName(dto.getCompanyName()==null|| dto.getCompanyName().isBlank()?publisher.getCompanyName():dto.getCompanyName());
		publisher.setAddress(dto.getAddress());
		
		publisherRepository.save(publisher);

	}

}
