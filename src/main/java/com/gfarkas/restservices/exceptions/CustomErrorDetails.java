package com.gfarkas.restservices.exceptions;

import java.util.Date;

public class CustomErrorDetails {
	
	private Date timeStemp;
	private String message;
	private String errordetails;
	
	public CustomErrorDetails(Date timeStemp, String message, String errordetails) {
		super();
		this.timeStemp = timeStemp;
		this.message = message;
		this.errordetails = errordetails;
	}


	public Date getTimeStemp() {
		return timeStemp;
	}

	public String getMessage() {
		return message;
	}

	public String getErrordetails() {
		return errordetails;
	}
	
	
	
	

}
