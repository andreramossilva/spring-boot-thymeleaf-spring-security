package br.com.spring.boot.thymeleaf.spring.security.core.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.spring.boot.thymeleaf.spring.security.core.entities.Role;

public interface RoleRepository extends CrudRepository<Role, String> {}
