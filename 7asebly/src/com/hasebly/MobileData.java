package com.hasebly;

public class MobileData {

	private String mobileNumber, mPin;
	
	protected MobileData(String mobileNumber, String mPin)
	{
		this.mobileNumber = mobileNumber;
		this.mPin = mPin;
	}

	protected String getMobileNumber() {
		return mobileNumber;
	}

	protected String getmPin() {
		return mPin;
	}
	
}
