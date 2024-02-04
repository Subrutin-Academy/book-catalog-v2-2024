package com.subrutin.catalog.web;

import java.net.URI;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.subrutin.catalog.dto.PublisherCreateRequestDTO;
import com.subrutin.catalog.dto.PublisherUpdateRequestDTO;
import com.subrutin.catalog.service.PublisherService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class PublisherResource {

	private final PublisherService publisherService;

	@PostMapping("/v1/publisher")
	public ResponseEntity<Void> createNewPublisher(@RequestBody @Valid PublisherCreateRequestDTO dto) {
		publisherService.createPublisher(dto);
		return ResponseEntity.created(URI.create("/v1/publisher")).build();
	}

	@PutMapping("/v1/publisher/{publisherId}")
	public ResponseEntity<Void> updatePublisher(@PathVariable String publisherId,
			@RequestBody @Valid PublisherUpdateRequestDTO dto) {
		publisherService.updatePublisher(publisherId, dto);
		return ResponseEntity.ok().build();
	}

}
