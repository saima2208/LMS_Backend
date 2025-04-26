package org.saima.LMS.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;

@Getter
public class JwtUserDetails implements UserDetails {
	private final Long id;
	private final String username;
	private final String email;
	private final String role;
	private final Collection<? extends GrantedAuthority> authorities;

	public JwtUserDetails(Long id, String username, String email, String role) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.role = role;
		this.authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return username;
	}

}