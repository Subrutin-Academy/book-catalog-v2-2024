package com.subrutin.catalog.security.model;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccessJWTToken implements Token {
	
	private final String rawToken;
	
	private Claims claims;

	@Override
	public String getToken() {
		return this.getRawToken();
	}

}
