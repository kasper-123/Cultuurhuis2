package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.restclients.UserClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("users")
class UserController {
private final UserClient client;

UserController(UserClient client) {
	this.client = client;
}
@GetMapping("{id}")
public ModelAndView getUser(@PathVariable long id){
	var modelAndView=new ModelAndView("user");
	client.findById(id).ifPresent(user -> modelAndView.addObject(user));
	return modelAndView;
}
}
