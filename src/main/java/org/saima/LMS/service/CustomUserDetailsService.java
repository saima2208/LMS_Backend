package org.saima.LMS.service;

import org.saima.LMS.model.CustomUserDetails;
import org.saima.LMS.model.User;
import org.saima.LMS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + email));

		return new CustomUserDetails(user);
	}

	@Transactional
	public UserDetails loadUserById(Long user_id) {
		User user = userRepository.findById(user_id)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + user_id));

		return new CustomUserDetails(user);
	}
}