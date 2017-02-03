package br.com.spring.boot.thymeleaf.spring.security.core.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.spring.boot.thymeleaf.spring.security.core.repository.UserRepositoryCustom;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom{

	@PersistenceContext
	private EntityManager manager; 
	
	@Override
	public UserDetails getByEmail(String email) {
		StringBuilder hql = new StringBuilder();
		hql.append("from User u ");
		hql.append("where u.email = :email");
		Query query = manager.createQuery(hql.toString());
		query.setParameter("email", email);
		return (UserDetails) query.getSingleResult();
	}
	
}
