package com.portalizer.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.portalizer.model.Address;
import com.portalizer.model.User;
import com.portalizer.service.AddressServiceImpl;
import com.portalizer.service.SecurityServiceImpl;
import com.portalizer.service.UserServiceImpl;
import com.portalizer.utilities.PatternValidator;

@RestController
@RequestMapping(value="/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {

	private Logger log = Logger.getLogger(this.getClass());
	public PatternValidator pv;
	
	@Autowired
	public UserServiceImpl userService;
	
	@Autowired
	public AddressServiceImpl addressService;
	
	@Autowired
	public SecurityServiceImpl securityService;
	
	public UserRestController(){
		pv = new PatternValidator();
	}
	
	@RequestMapping(value="/find/all", method=RequestMethod.GET)
	public ResponseEntity<List<User>> findAllUser(){
		List<User> users = userService.getAll();
		if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);

	}
	
	@RequestMapping(value="/find", method=RequestMethod.GET)
	public List<User> findUserByName(
			@RequestParam(value="lastname", required = false) String lastname,
			@RequestParam(value="firstname", required = false) String firstname
			) {
		
		//double checker sanity input for the first name/last name
		List<User> _userDetails = null;
		
		if(log.isDebugEnabled()) {
			log.debug("Find firstname/lastname equals " + lastname + " " + firstname);
		}
		
		if(firstname != null && firstname != ""){
			_userDetails = userService.findByfirstName(firstname);
		} else if(lastname !=null && lastname != "") {
			_userDetails = userService.findBylastName(lastname);
		}
		
		return _userDetails;
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public ResponseEntity<User> findUserById(@PathVariable("id") Long id){
		if(log.isInfoEnabled()){
			log.info("Find user by id equals " + id);
		}

		User user = userService.findById(id);
		if (user == null){
	           return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	    }
	    return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value="/find/{custuuid}", method=RequestMethod.GET)
	public User findUserByCustUUID(@PathVariable("custuuid") String custUUID){
		if(log.isDebugEnabled()){
			log.debug(custUUID);
		}
		return userService.findByCustUUID(custUUID);
	}
	
	/* functionality wise this is only administrator related roles that allowed to add a new user to the system */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResponseEntity<Void> addUser(@RequestBody User user, UriComponentsBuilder ucBuilder){
		
		if(userService.isUserExist(user)){
			if(log.isInfoEnabled()){ 
				log.info("User with Email Address " + user.getEmail() + " is already exist."); 
			}
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
			HttpHeaders headers = new HttpHeaders();
			if(pv.validateEmail(user.getEmail())){				
				userService.save(user);
				if(log.isInfoEnabled()){ log.info("New user created. User id " + user.getId()); }
				headers.setLocation(ucBuilder.path("{id}").buildAndExpand(user.getId()).toUri());
				
				return new ResponseEntity<Void>(HttpStatus.CREATED);
			} else {
				if(log.isInfoEnabled()){
					log.info("User Email Validation Fails " + user.getEmail());
				}
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}	
		}
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@RequestBody User user, UriComponentsBuilder ucBuilder){
		
		HttpHeaders headers = new HttpHeaders();
		if(user.getId() == null) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		} else if ((user.getId() != null) || (user.getId().toString() != "")) {
			userService.update(user);
			headers.setLocation(ucBuilder.path("{id}").buildAndExpand(user.getId()).toUri());
			if(log.isInfoEnabled()) log.info("User details updated. User id " + user.getId());
			return new ResponseEntity<User>(HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/* user address related administrative controller methods */
	
	@RequestMapping(value="{id}/address/update", method=RequestMethod.PUT)
	public ResponseEntity<User> updateUserAddress(@PathVariable("id") Long id, @RequestBody Address address, UriComponentsBuilder ucBuilder){
		
		HttpHeaders headers = new HttpHeaders();
		if ((id != null) && (id.toString() != "")){
			addressService.update(address, id);
			headers.setLocation(ucBuilder.path("{id}").buildAndExpand(address.getId()).toUri());
			
			if (log.isInfoEnabled()) {
				log.info("User address details updated. User id " + id);
			}
			
			return new ResponseEntity<User>(HttpStatus.OK);
		} else if((id == null) || (id.toString() == "")) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
	}
	
}
