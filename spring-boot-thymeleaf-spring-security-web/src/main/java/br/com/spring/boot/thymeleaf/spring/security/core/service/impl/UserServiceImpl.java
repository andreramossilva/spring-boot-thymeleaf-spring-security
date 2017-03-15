package br.com.spring.boot.thymeleaf.spring.security.core.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

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
	public UserDetails loadUserByUsername(final String email) {
		return (UserDetails) userRepository.findByEmail(email); 
	}
		
	@Override
	public User findById(final Integer id){
		return userRepository.findOne(id);
	}
	
	@Override
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public void save(User user, final boolean shouldChangePassword) throws ServiceException, ValidationException {
		try{
			
			validate(user);
			
			if(shouldChangePassword){
				configPassword(user);
			}
			
			userRepository.save(user);
			
		} catch (ValidationException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	private void configPassword(User user) {
		if(!StringUtils.isEmptyOrWhitespace(user.getPassword())){
			BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
			user.setPassword(crypt.encode(user.getPassword()));
		} else {
			User u = findById(user.getId());
			user.setPassword(u.getPassword());
		}
	}

	@Override
	public User findByEmail(final String email) {
		return (User) userRepository.findByEmail(email);
	}
	
	private void validate(User user) throws ValidationException {
		
		if(user.getId() == null){
			User validateUser = findByEmail(user.getEmail());
			if(validateUser  != null){
				throw new ValidationException("email", "field.invalid.user.email.exists");
			}
		
		}
	}

	@Override
	public void changeStatus(final Integer id, final boolean status) throws ServiceException, ValidationException {
		User user = findById(id);
		user.setStatus(status);
		save(user, false);
	}

}
