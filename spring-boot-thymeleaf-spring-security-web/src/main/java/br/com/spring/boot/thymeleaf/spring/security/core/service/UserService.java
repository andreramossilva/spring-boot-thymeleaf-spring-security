package br.com.spring.boot.thymeleaf.spring.security.core.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import br.com.spring.boot.thymeleaf.spring.security.core.entities.User;

public interface UserService extends UserDetailsService {
	UserDetails loadUserByUsername(String email);
	List<User> findAll();
}
