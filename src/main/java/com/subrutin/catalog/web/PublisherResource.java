package com.subrutin.catalog.web;

import java.net.URI;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.subrutin.catalog.annotation.LogThisMethod;
import com.subrutin.catalog.dto.PublisherCreateRequestDTO;
import com.subrutin.catalog.dto.PublisherListResponseDTO;
import com.subrutin.catalog.dto.PublisherUpdateRequestDTO;
import com.subrutin.catalog.dto.ResultPageResponseDTO;
import com.subrutin.catalog.exception.BadRequestException;
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
	
	@LogThisMethod
	@GetMapping("/v1/publisher")
	public ResponseEntity<ResultPageResponseDTO<PublisherListResponseDTO>> findPublisherList(
			@RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages, 
			@RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
			@RequestParam(name="sortBy", required = true, defaultValue = "name") String sortBy,
			@RequestParam(name="direction", required = true, defaultValue = "asc") String direction,
			@RequestParam(name="publisherName", required = false) String publisherName){
		if(pages<0) throw new BadRequestException("invalid pages");
		return ResponseEntity.ok().body(publisherService.findPublisherList(pages, limit, sortBy, direction, publisherName));
	}

}
