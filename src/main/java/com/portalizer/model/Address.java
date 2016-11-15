package com.portalizer.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="address")
public class Address {
	
	private Long id;
	
	@Column(name="street_name")
	private String streetName;
	
	@Column(name="city")
	private String city;
	
	@Column(name="postal_code")
	private String postalCode;
	
	@Column(name="country")
	private String country;
	
	@Column(name="area_code")
	private String areaCode;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	private User user;
	
	public Address() {
		super();
	}
	public Address(Long id, String streetName, String city, String postalCode, String country, String areaCode,
			String phoneNumber) {
		super();
		this.id = id;
		this.streetName = streetName;
		this.city = city;
		this.postalCode = postalCode;
		this.country = country;
		this.areaCode = areaCode;
		this.phoneNumber = phoneNumber;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@JsonBackReference
	@OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
	public User getUser(){
		return user;
	}
	
	public void setUser(User user){
		this.user = user;
	}
	
}
