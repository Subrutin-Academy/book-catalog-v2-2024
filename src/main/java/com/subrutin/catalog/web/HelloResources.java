package com.subrutin.catalog.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subrutin.catalog.service.GreetingService;

@RestController
public class HelloResources {

	private GreetingService greetingService;
	
	public HelloResources(GreetingService greetingService) {
		super();
		this.greetingService = greetingService;
	}

	@GetMapping("/hello")
	public String helloWorld() {
		return greetingService.sayGreeting();
	}
}