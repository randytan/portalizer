package com.portalizer.service;

import java.util.List;

import com.portalizer.model.Address;

public interface AddressService {
	
	public List<Address> findByPostalCode(String postalCode);
	public List<Address> findByCountry(String country);
	
	public Address findByAddressId(Long id);
	public Address findByPhoneNumber(String phoneNumber);
	
	public Address save(Address address);
	public Address update(Address address, Long id);

}
