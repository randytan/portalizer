package com.portalizer.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.portalizer.model.Address;
import com.portalizer.repos.AddressRepository;

@Transactional
@Configuration
public class AddressServiceImpl implements AddressService {
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	public AddressRepository addressRepository;
	
	public List<Address> findByPostalCode(String postalCode){
		List<Address> addressByPostalCode = null;
		
		if ((postalCode != null) || (postalCode != "")) addressByPostalCode = addressRepository.findByPostalCode(postalCode);
		return addressByPostalCode;
	}
	
	public List<Address> findByCountry(String country){
		List<Address> addressByCountry = null;
		if((country != null) || (country != "")) addressByCountry = addressRepository.findByCountry(country);
		return addressByCountry;
	}
	
	public Address findByAddressId(Long id){
		return addressRepository.findOne(id);
	}
	
	public Address findByPhoneNumber(String phoneNumber){
		Address addressByPhoneNum = null;
		if((phoneNumber != null) || (phoneNumber !="")) addressByPhoneNum = addressRepository.findByPhoneNumber(phoneNumber);
		return addressByPhoneNum;
	}
	
	public Address save(Address address){
		if ((address.getUser() != null) && ((address.getStreetName() != null) || (address.getStreetName() != ""))) 
		addressRepository.save(address);
		return address;
	}
	
	public Address update(Address address, Long id){
		Address _address = addressRepository.findOne(address.getId());
		if ((address.getId() != null) && (id != null)){
			_address.setAreaCode(address.getAreaCode());
			_address.setCity(address.getCity());
			_address.setCountry(address.getCountry());
			_address.setPhoneNumber(address.getPhoneNumber());
			_address.setPostalCode(address.getPostalCode());
			_address.setStreetName(address.getStreetName());
		}
		return addressRepository.save(_address);
			
	}
	
}
