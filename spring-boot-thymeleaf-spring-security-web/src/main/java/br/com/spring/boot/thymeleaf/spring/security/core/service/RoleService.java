package br.com.spring.boot.thymeleaf.spring.security.core.service;

import java.util.List;

import br.com.spring.boot.thymeleaf.spring.security.core.entities.Role;

public interface RoleService {
	List<Role> findAll();
}
