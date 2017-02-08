package br.com.spring.boot.thymeleaf.spring.security.core.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.spring.boot.thymeleaf.spring.security.core.entities.User;
import br.com.spring.boot.thymeleaf.spring.security.core.repository.UserRepositoryCustom;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom{

	@PersistenceContext
	private EntityManager manager; 
	
	@Override
	public UserDetails findByEmail(String email) {
		
		StringBuilder hql = new StringBuilder();
		hql.append("from User u ");
		hql.append("where u.email = :email");
		
		Query query = manager.createQuery(hql.toString());
		query.setParameter("email", email);
		
		try{
			return (UserDetails) query.getSingleResult();
		} catch (NoResultException e){
			return null;
		}
		
	}

	@Override
	public User findByFederalId(String federalId) {
		
		StringBuilder hql = new StringBuilder();
		hql.append("from User u ");
		hql.append("where u.federalId = :federalId");
		
		Query query = manager.createQuery(hql.toString());
		query.setParameter("federalId", federalId);
		
		try{
			return (User) query.getSingleResult();
		} catch (NoResultException e){
			return null;
		}
	}
	
}
