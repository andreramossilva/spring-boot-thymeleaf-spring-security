package br.com.spring.boot.thymeleaf.spring.security.web.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.spring.boot.thymeleaf.spring.security.core.entities.User;
import br.com.spring.boot.thymeleaf.spring.security.core.enums.RoleEnum;
import br.com.spring.boot.thymeleaf.spring.security.core.repository.UserRepository;
import br.com.spring.boot.thymeleaf.spring.security.core.repository.impl.UserRepositoryImpl;
import br.com.spring.boot.thymeleaf.spring.security.core.service.UserService;
import br.com.spring.boot.thymeleaf.spring.security.core.service.impl.UserServiceImpl;
import br.com.spring.boot.thymeleaf.spring.security.web.constants.action.LoginActionConstants;
import br.com.spring.boot.thymeleaf.spring.security.web.constants.action.LogoutActionConstants;
import br.com.spring.boot.thymeleaf.spring.security.web.constants.action.UserActionConstants;
import br.com.spring.boot.thymeleaf.spring.security.web.controller.HomeController;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses=UserRepository.class)
@EntityScan(basePackageClasses=User.class)
@ComponentScan(basePackageClasses = {HomeController.class, UserServiceImpl.class, UserRepositoryImpl.class})
public class Configuration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(Configuration.class, args);
	}

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		
		.antMatchers(UserActionConstants.ROOT_ACTION).hasRole(RoleEnum.ROLE_ADMIN.getValue())
		.antMatchers(UserActionConstants.ADD_ACTION).hasRole(RoleEnum.ROLE_ADMIN.getValue())
		
		.anyRequest().authenticated()
		.and().formLogin().loginPage(LoginActionConstants.LOGIN_ACTION).permitAll()
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher(LogoutActionConstants.LOGOUT_ACTION));
		
	}
	
}

