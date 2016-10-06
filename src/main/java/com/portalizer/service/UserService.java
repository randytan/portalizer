package com.portalizer.service;

import java.util.List;

import com.portalizer.model.User;

public interface UserService {
	
	public List<User> getAll();
	
	public List<User> findBylastName(String lastname);
	public List<User> findByfirstName(String firstname);
	
	public User findUserByEmail(String email);
	public User findByCustUUID(String custUUID);
	public User findById(long id);
	
	public User save(User user);
	public User update(User user);
	
	public void delete(long id);

}
