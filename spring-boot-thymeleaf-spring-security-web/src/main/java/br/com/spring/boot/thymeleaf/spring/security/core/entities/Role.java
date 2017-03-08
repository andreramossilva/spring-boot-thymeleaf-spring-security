package br.com.spring.boot.thymeleaf.spring.security.core.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="role")
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 5521697581691999052L;
	
	@Id
	private String description;
	
	@Override
	public String getAuthority() {
		return description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
