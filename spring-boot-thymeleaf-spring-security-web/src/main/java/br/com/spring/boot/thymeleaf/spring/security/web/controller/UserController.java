package br.com.spring.boot.thymeleaf.spring.security.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.spring.boot.thymeleaf.spring.security.core.entities.User;
import br.com.spring.boot.thymeleaf.spring.security.core.service.RoleService;
import br.com.spring.boot.thymeleaf.spring.security.core.service.UserService;
import br.com.spring.boot.thymeleaf.spring.security.core.service.exceptions.ServiceException;
import br.com.spring.boot.thymeleaf.spring.security.core.service.exceptions.ValidationException;
import br.com.spring.boot.thymeleaf.spring.security.web.constants.action.UserActionConstants;
import br.com.spring.boot.thymeleaf.spring.security.web.constants.html.UserHtmlConstants;
import br.com.spring.boot.thymeleaf.spring.security.web.validation.UserValidation;

@Controller
@RequestMapping(value=UserActionConstants.ROOT_ACTION)
public class UserController {
	
	private static final String ROLES = "roles";

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.addValidators(new UserValidation());
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView model = new ModelAndView(UserHtmlConstants.LIST);
		List<User> users = userService.findAll();
		model.addObject("users", users);
		return model;
	}
	
	@RequestMapping(value=UserActionConstants.EDIT_ACTION, method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable int id){
		return viewUser(userService.findById(id));
	}
	
	@RequestMapping(value=UserActionConstants.ADD_ACTION, method=RequestMethod.GET)
	public ModelAndView add(User user){
		return viewUser(user);
	}
	
	private ModelAndView viewUser(User user){
		ModelAndView model = new ModelAndView(UserHtmlConstants.USER);
		
		if(user.getId() != null){
			model.addObject("user", user);
		}
		
		model.addObject(ROLES, roleService.findAll());
		return model;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView save(@Valid User user, BindingResult result){
		
		if(!result.hasErrors()){
			try {
				userService.save(user);
				return list();
			} catch (ValidationException e) {
				result.rejectValue(e.getField() , e.getMessage());
				return viewUser(user);
			} catch (ServiceException e) {
				return viewUser(user);
			}
		} else {
			return viewUser(user);
		}
		
	}
	
}
