package br.com.spring.boot.thymeleaf.spring.security.core.repository;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepositoryCustom {
	UserDetails getByEmail(String email);
}
