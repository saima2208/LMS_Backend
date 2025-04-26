package org.saima.LMS.model;

import java.util.Collection;
import java.util.Collections;

import org.saima.LMS.constants.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public record CustomUserDetails(User user) implements UserDetails {

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	public Long getId() {
		return user.getId();
	}

	public String getEmail() {
		return user.getEmail();
	}

	public Role getRole() {
		return user.getRole();
	}
}
