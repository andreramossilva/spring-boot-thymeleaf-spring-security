package br.com.spring.boot.thymeleaf.spring.security.core.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.spring.boot.thymeleaf.spring.security.core.entities.User;
import br.com.spring.boot.thymeleaf.spring.security.core.repository.UserRepository;
import br.com.spring.boot.thymeleaf.spring.security.core.service.UserService;
import br.com.spring.boot.thymeleaf.spring.security.core.service.exceptions.ServiceException;
import br.com.spring.boot.thymeleaf.spring.security.core.service.exceptions.ValidationException;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) {
		return (UserDetails) userRepository.findByEmail(email); 
	}

	@Override
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public void save(User user) throws ServiceException, ValidationException {
		try{
			
			validate(user);
			
			BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
			user.setPassword(crypt.encode(user.getPassword()));
			
			userRepository.save(user);
			
		} catch (ValidationException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public User findByFederalId(String federalId) {
		return userRepository.findByFederalId(federalId);
	}

	@Override
	public User findByEmail(String email) {
		return (User) userRepository.findByEmail(email);
	}
	
	private void validate(User user) throws ValidationException {
		User u = findByFederalId(user.getFederalId());
		if(u  != null){
			throw new ValidationException("federalId", "field.invalid.user.federal.id.exists.personal");
		}
		
		u = findByEmail(user.getEmail());
		if(u  != null){
			throw new ValidationException("email", "field.invalid.user.email.exists");
		}
	}

}
