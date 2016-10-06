package com.portalizer.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.portalizer.model.User;
import com.portalizer.repos.UserRepository;

@Transactional
@Configuration
public class UserServiceImpl implements UserService {
	
	@Autowired
	public UserRepository userRepository;
	
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
	public User findById(long id){
		return userRepository.findOne(id);
	}
	
	public User save(User user){
		return userRepository.save(user);
	}
	
	public User update(User user){
		User _user = userRepository.findOne(user.getId());
		if (_user != null){
			_user.setAddress(user.getAddress());
			_user.setEmail(user.getEmail());
			_user.setFirstName(user.getFirstName());
			_user.setMiddleName(user.getMiddleName());
			_user.setLastName(user.getLastName());
			_user.setGender(user.getGender());
			_user.setPassword(user.getPassword());
		} else {
			//do nothing
		}
		
		return userRepository.save(user);
	}
	
	public void delete(long id){
		userRepository.delete(id);
	}

}
