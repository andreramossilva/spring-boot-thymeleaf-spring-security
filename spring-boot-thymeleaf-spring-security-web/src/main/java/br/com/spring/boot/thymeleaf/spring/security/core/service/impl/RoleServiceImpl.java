package br.com.spring.boot.thymeleaf.spring.security.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spring.boot.thymeleaf.spring.security.core.entities.Role;
import br.com.spring.boot.thymeleaf.spring.security.core.repository.RoleRepository;
import br.com.spring.boot.thymeleaf.spring.security.core.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public List<Role> findAll() {
		return (List<Role>) roleRepository.findAll();
	}

}
