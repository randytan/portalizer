package com.portalizer.service;

public interface SecurityService {
	public String findLoggedInUsername();
	
	void autologin(String email, String password);
}
