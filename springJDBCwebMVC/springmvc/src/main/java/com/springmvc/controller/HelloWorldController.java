package com.springmvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.dao.UserDao;
import com.springmvc.model.User;

@Controller
public class HelloWorldController {

	@Autowired
	private UserDao userDaoImpl;

	@RequestMapping(value = "/")
	private ModelAndView helloWorld() {

		ModelAndView model = new ModelAndView("Hello");

		List<User> allUser = userDaoImpl.allUsers();

		model.addObject("msg", "tomcatserverr");
		model.addObject("listUser", allUser);

		return model;
	}

	@RequestMapping(value = "/newUser", method = RequestMethod.GET)
	private ModelAndView addUser(ModelAndView model) {
		User newUser = new User();

		model.addObject("user", newUser);
		model.setViewName("NewUser");

		return model;

	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	private ModelAndView saveUser(@ModelAttribute User user) {
		System.out.println("saveController" + user.getId());
		userDaoImpl.addUserorUpdate(user);

		return new ModelAndView("redirect:/");

	}

	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
	private ModelAndView editUser(HttpServletRequest request) { // can we use path variable also
		int id = Integer.parseInt(request.getParameter("id"));
		ModelAndView model = new ModelAndView();
		User Specificuser = userDaoImpl.getUser(id);

		model.addObject("user", Specificuser);

		model.setViewName("NewUser");

		return model;

	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	private ModelAndView delUser(HttpServletRequest request) {

		int id = Integer.parseInt(request.getParameter("id"));
		userDaoImpl.deleteUser(id);
		return new ModelAndView("redirect:/");

	}

}
