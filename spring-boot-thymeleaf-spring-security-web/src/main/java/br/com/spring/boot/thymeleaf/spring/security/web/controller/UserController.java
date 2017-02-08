package br.com.spring.boot.thymeleaf.spring.security.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.spring.boot.thymeleaf.spring.security.core.entities.User;
import br.com.spring.boot.thymeleaf.spring.security.core.service.UserService;
import br.com.spring.boot.thymeleaf.spring.security.core.service.exceptions.ServiceException;
import br.com.spring.boot.thymeleaf.spring.security.core.service.exceptions.ValidationException;
import br.com.spring.boot.thymeleaf.spring.security.web.constants.action.UserActionConstants;
import br.com.spring.boot.thymeleaf.spring.security.web.constants.html.UserHtmlConstants;
import br.com.spring.boot.thymeleaf.spring.security.web.validation.UserValidation;

@Controller
@RequestMapping(value=UserActionConstants.USER_ROOT_ACTION)
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.addValidators(new UserValidation());
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView add(User user){
		ModelAndView model = new ModelAndView(UserHtmlConstants.USER);
		return model;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView save(@Valid User user, BindingResult result){
		
		ModelAndView model = new ModelAndView(UserHtmlConstants.USER);
		
		if(!result.hasErrors()){
			try {
				userService.save(user);
			} catch (ServiceException e) {
				e.printStackTrace();
			} catch (ValidationException e) {
				result.rejectValue(e.getField() , e.getMessage());
			}
		}
		
		return model;
	}
	
}
