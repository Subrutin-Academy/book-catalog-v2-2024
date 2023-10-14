package com.subrutin.catalog.service.impl;

import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.subrutin.catalog.config.ApplicationProperties;
import com.subrutin.catalog.service.GreetingService;

@Service
public class GreetingServiceImpl implements GreetingService {

	private ApplicationProperties appProperties;

	public GreetingServiceImpl(ApplicationProperties appProperties) {
		super();
		this.appProperties = appProperties;
	}

	
	@Override
	public String sayGreeting() {
		TimeZone timezone = TimeZone.getTimeZone(appProperties.getTimezone());
		return appProperties.getWelcomeText() + ", our timezone :" + timezone.getDisplayName() + ", our currency:"
				+ appProperties.getCurrency();	
		}

}
