package com.portalizer.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portalizer.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
	
	List<Address> findByPostalCode(String postalCode);
	List<Address> findByCountry(String country);
	
	Address findByPhoneNumber(String phoneNumber);
}
