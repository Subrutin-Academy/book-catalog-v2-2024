package com.subrutin.catalog.security.util;

import java.util.Date;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.security.core.GrantedAuthority;

import com.subrutin.catalog.security.model.AccessJWTToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecretKeyBuilder;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JWTTokenFactory {
	
	private final Key secret;

	public AccessJWTToken createAccessJWTToken(String username, Collection<? extends GrantedAuthority> authorities) {
		Claims claims = Jwts.claims().subject(username)
				.add("scope", authorities.stream().map(a -> a.getAuthority()).collect(Collectors.toList()))
				.build();

//		claims.put("scope", authorities.stream().map(a -> a.getAuthority()).collect(Collectors.toList()));
//		Claims claims = Jwts.claims().setSubject(username);
//		claims.put("scope", authorities.stream().map(a -> a.getAuthority()).collect(Collectors.toList()));
		LocalDateTime currentTime = LocalDateTime.now();
		LocalDateTime expiredTime = currentTime.plusMinutes(15);
		
		Date currentTimeDate = Date.from(currentTime.atZone(ZoneId.of("Asia/Jakarta")).toInstant());
		Date expiredTimeDate = Date.from(expiredTime.atZone(ZoneId.of("Asia/Jakarta")).toInstant());
		
		String token = Jwts.builder().claims(claims)
			.issuer("http://subrutin.com").issuedAt(currentTimeDate)
			.expiration(expiredTimeDate)
			.signWith(secret).compact();

		return new AccessJWTToken(token, claims);

	}

}
