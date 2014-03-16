package com.hasebly;

public class CApduProcessingOptions extends CApdu{
	
	public CApduProcessingOptions()
	{
		super();
		command = returnListFromString("80 A8 00 00 02 83 00");
	}

	
	
}
