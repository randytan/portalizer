package com.portalizer.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portalizer.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);
	User findByCustUUID(String custUUID);
	User findById(long id);
	
	List<User> findBylastName(String lastname);
	List<User> findByfirstName(String firstName);
}
