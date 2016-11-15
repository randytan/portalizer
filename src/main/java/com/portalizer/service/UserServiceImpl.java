package com.portalizer.service;

import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.portalizer.model.User;
import com.portalizer.repos.RoleRepository;
import com.portalizer.repos.UserRepository;

@Transactional
@Configuration
public class UserServiceImpl implements UserService {
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder byCryptPasswordEncoder;
	
	public List<User> getAll(){
		return userRepository.findAll();
	}
	
	public List<User>findBylastName(String lastName){
		return userRepository.findBylastName(lastName);
	}
	
	public List<User>findByfirstName(String firstname){
		return userRepository.findByfirstName(firstname);
	}
	
	public User findUserByEmail(String email){
		return userRepository.findByEmail(email);
	}
	public User findByCustUUID(String custUUID){
		return userRepository.findByCustUUID(custUUID);
	}
	public User findById(Long id){
		return userRepository.findOne(id);
	}
	
	public User save(User user){
		/* set user password in hashing format with it's roles  */
		user.setPassword(byCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<>(roleRepository.findAll()));
		return userRepository.save(user);
	}
	
	public User update(User user){
		
		if(log.isInfoEnabled()) {
			log.info("User id " +user.getId()+ " will be updated.");
		}
		
		User _user = userRepository.findOne(user.getId());
		if (_user != null){
			_user.setAddress(user.getAddress());
			_user.setEmail(user.getEmail());
			_user.setFirstName(user.getFirstName());
			_user.setMiddleName(user.getMiddleName());
			_user.setLastName(user.getLastName());
			_user.setGender(user.getGender());
			_user.setPassword(byCryptPasswordEncoder.encode(user.getPassword()));
		} else {
			//do nothing
		}
		return userRepository.save(_user);
	}
	
	public void delete(Long id){
		userRepository.delete(id);
	}
	
	public boolean isUserExist(User user){
		boolean userExist = true;
		if(user != null) {
			String email = user.getEmail();
			User _userDatabaseCheck = userRepository.findByEmail(email);
			if(_userDatabaseCheck == null){
				userExist = false;
			} 
		} 
		return userExist;
	}

}
