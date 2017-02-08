package br.com.spring.boot.thymeleaf.spring.security.core.repository;

import org.springframework.security.core.userdetails.UserDetails;

import br.com.spring.boot.thymeleaf.spring.security.core.entities.User;

public interface UserRepositoryCustom {
	UserDetails findByEmail(String email);
	User findByFederalId(String federalId);
}
