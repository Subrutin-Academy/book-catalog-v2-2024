package com.subrutin.catalog.security.model;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class RawAccessJwtToken implements Token {
	
	private String token;
	
	
	
	public RawAccessJwtToken(String token) {
		super();
		this.token = token;
	}

	public Jws<Claims> parseClaims(SecretKey secret){
		return Jwts.parser().verifyWith(secret).build().parseSignedClaims(this.token);
	}

	@Override
	public String getToken() {
		return this.token;
	}

}
