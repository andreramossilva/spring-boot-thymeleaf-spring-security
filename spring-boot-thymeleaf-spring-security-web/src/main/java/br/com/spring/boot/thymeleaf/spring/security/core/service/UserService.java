package br.com.spring.boot.thymeleaf.spring.security.core.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import br.com.spring.boot.thymeleaf.spring.security.core.entities.User;
import br.com.spring.boot.thymeleaf.spring.security.core.service.exceptions.ServiceException;
import br.com.spring.boot.thymeleaf.spring.security.core.service.exceptions.ValidationException;

public interface UserService extends UserDetailsService {
	UserDetails loadUserByUsername(final String email);
	List<User> findAll();
	void save(User user, final boolean shouldChangePassword) throws ServiceException, ValidationException;
	void changeStatus(final Integer id, final boolean status) throws ServiceException, ValidationException;
	User findByEmail(final String email);
	User findById(final Integer id);
}
