package com.subrutin.catalog.security.model;

import java.security.Key;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class RawAccessJwtToken implements Token{
	
	private String token;
	

	public RawAccessJwtToken(String token) {
		super();
		this.token = token;
	}

	public Jws<Claims> parseClaims(SecretKey signingKey) {
		System.out.println(token);
		 return Jwts.parser().verifyWith(signingKey).build().parseSignedClaims(token);
	}


	@Override
	public String getToken() {
		return this.token;
	}

}
