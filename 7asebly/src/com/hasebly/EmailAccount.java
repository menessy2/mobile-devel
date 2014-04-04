package com.hasebly;

public class EmailAccount {

	String email,cvv;
	
	protected EmailAccount(String email, String cvv)
	{
		this.email = email;
		this.cvv = cvv;
	}

	protected String getEmail() {
		return email;
	}

	protected String getCvv() {
		return cvv;
	}
	
}
