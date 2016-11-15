package com.portalizer.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class PatternValidator {
	
	public Logger log = Logger.getLogger(this.getClass());
	
	private Pattern pattern;
	private Matcher matcher;
	
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public boolean validateEmail(final String hex){
		if(log.isInfoEnabled()) log.info("Email sent from user - "+hex);
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(hex);		
		return matcher.matches();
	}
	
}
