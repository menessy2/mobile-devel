package com.hasebly;

public class CApduRead extends CApdu{
	
	public CApduRead(String shortFileIndicator, String recordNumber)
	{
		super();
		command = returnListFromString("00 B2" + recordNumber + shortFileIndicator);		
	}
	
	public CApduRead(byte shortFileIndicator, byte recordNumber)
	{
		super();
		command = returnListFromString("00 B2 " + returnHexValue(recordNumber) + returnHexValue(shortFileIndicator));
	}

	
}
