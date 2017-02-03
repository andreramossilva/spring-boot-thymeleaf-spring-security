package br.com.spring.boot.thymeleaf.spring.security.core.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.spring.boot.thymeleaf.spring.security.core.entities.User;
import br.com.spring.boot.thymeleaf.spring.security.core.repository.UserRepository;
import br.com.spring.boot.thymeleaf.spring.security.core.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) {
		return (UserDetails) userRepository.getByEmail(email); 
	}

	@Override
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

}
