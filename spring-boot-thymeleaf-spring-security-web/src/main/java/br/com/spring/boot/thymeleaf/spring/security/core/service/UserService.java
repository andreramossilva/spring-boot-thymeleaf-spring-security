package br.com.spring.boot.thymeleaf.spring.security.core.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import br.com.spring.boot.thymeleaf.spring.security.core.entities.User;
import br.com.spring.boot.thymeleaf.spring.security.core.service.exceptions.ServiceException;
import br.com.spring.boot.thymeleaf.spring.security.core.service.exceptions.ValidationException;

public interface UserService extends UserDetailsService {
	UserDetails loadUserByUsername(String email);
	List<User> findAll();
	void save(User user) throws ServiceException, ValidationException;
	User findByFederalId(String federalId);
	User findByEmail(String email);
}
