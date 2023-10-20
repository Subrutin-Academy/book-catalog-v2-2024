package com.subrutin.catalog.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subrutin.catalog.service.GreetingService;

@RestController
public class HelloResources {

	Logger log = LoggerFactory.getLogger(HelloResources.class);

	private GreetingService greetingService;
	
	public HelloResources(GreetingService greetingService) {
		super();
		this.greetingService = greetingService;
	}




	@GetMapping("/hello")
	public String helloWorld() {
		log.trace("this is log TRACE");
		log.debug("this is log DEBUG");
		log.info("this is log INFO");
		log.warn("this is log WARN");
		log.error("this is log ERROR");
		return greetingService.sayGreeting();
	}
}