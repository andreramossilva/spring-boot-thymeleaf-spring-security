package br.com.spring.boot.thymeleaf.spring.security.core.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.spring.boot.thymeleaf.spring.security.core.entities.User;

public interface UserRepository extends CrudRepository<User, Integer>, UserRepositoryCustom {}
