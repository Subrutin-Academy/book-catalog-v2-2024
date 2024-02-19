package com.subrutin.catalog.security.util;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;

import com.subrutin.catalog.security.model.AccessJWTToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JWTTokenFactory {
	
	private final Key secret;

	public AccessJWTToken createAccessJWTToken(String username, Collection<? extends GrantedAuthority> authorities) {
		Claims claims = Jwts.claims().subject(username)
		.add("scopes", authorities.stream().map(a->a.getAuthority()).collect(Collectors.toList())).build();
		
		//waktu kapan token dibuat
		LocalDateTime currentTime = LocalDateTime.now();
		Date currentTimeDate = Date.from(currentTime.atZone(ZoneId.of("Asia/Jakarta")).toInstant());
		
		//waktu kapan token expired
		LocalDateTime expiredTime = currentTime.plusMinutes(15);
		Date expiredTimeDate = Date.from(expiredTime.atZone(ZoneId.of("Asia/Jakarta")).toInstant());

		String token = Jwts.builder().claims(claims)
				.issuer("https://subrutin.com")
				.issuedAt(currentTimeDate)
				.expiration(expiredTimeDate)
				.signWith(secret).compact();
		
		return new AccessJWTToken(token, claims);
	}
}
