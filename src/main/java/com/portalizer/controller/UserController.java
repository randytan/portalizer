package com.portalizer.controller;

import java.util.List;

import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.portalizer.model.User;
import com.portalizer.service.UserServiceImpl;

@RestController
@RequestMapping(value="/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	public UserServiceImpl userService;
	
	@RequestMapping(value="/find/all", method=RequestMethod.GET)
	public List<User> findAllUser(){
		return userService.getAll();
	}
	
	@RequestMapping(value="/find", method=RequestMethod.GET)
	public List<User> findUserByName(
			@RequestParam(value="lastname", required = false) String lastname,
			@RequestParam(value="firstname", required = false) String firstname
			) {
		return userService.findByfirstName(firstname);
	}
	
	@RequestMapping(value="/find/{id}", method=RequestMethod.GET)
	public User findUserById(@PathParam("id") Long id){
		return userService.findById(id);
	}
}
