package br.com.spring.boot.thymeleaf.spring.security.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.spring.boot.thymeleaf.spring.security.web.constants.action.HomeActionConstants;
import br.com.spring.boot.thymeleaf.spring.security.web.constants.html.HomeHtmlConstants;

@Controller
public class HomeController {
	
	@RequestMapping(value=HomeActionConstants.HOME_ACTION)
	public String home(){
		return HomeHtmlConstants.HOME;
	}
	
}
