package br.com.spring.boot.thymeleaf.spring.security.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.spring.boot.thymeleaf.spring.security.web.constants.action.LoginActionConstants;
import br.com.spring.boot.thymeleaf.spring.security.web.constants.html.LoginHtmlConstants;

@Controller
public class LoginController {

	@RequestMapping(value=LoginActionConstants.LOGIN_ACTION, method=RequestMethod.GET)
	public String login(){
		return LoginHtmlConstants.LOGIN;
	}
	
}
