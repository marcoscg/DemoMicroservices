package com.mardeveloper.login.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mardeveloper.login.entity.Login;
import com.mardeveloper.login.repository.LoginRepository;

@Service
public class JwtClientDetailsService implements UserDetailsService {

	@Autowired
    private LoginRepository clientRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Login client = clientRepository.getByUsername(username);
		
		if (client.getUsername().equals(username)) {
			return new User(username, client.getPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}	
	
}
