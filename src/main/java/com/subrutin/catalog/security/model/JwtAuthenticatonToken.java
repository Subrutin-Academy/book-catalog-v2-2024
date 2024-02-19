package com.subrutin.catalog.security.model;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtAuthenticatonToken extends AbstractAuthenticationToken {
	
	private RawAccessJwtToken rawAcessJwtToken;
	
	private UserDetails userDetails;
	
	public JwtAuthenticatonToken(RawAccessJwtToken rawAccessJwtToken) {
		super(null);
		this.rawAcessJwtToken=rawAccessJwtToken;
		super.setAuthenticated(false);
	}

	public JwtAuthenticatonToken(UserDetails userDetails,
			Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.eraseCredentials();
		this.userDetails = userDetails;
		this.setAuthenticated(true);
	}

	@Override
	public Object getCredentials() {
		return this.rawAcessJwtToken;
	}

	@Override
	public Object getPrincipal() {
		return this.userDetails;
	}

	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
		this.rawAcessJwtToken=null;
	}

}
