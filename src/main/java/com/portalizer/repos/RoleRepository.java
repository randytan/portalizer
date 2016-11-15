package com.portalizer.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portalizer.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
}
