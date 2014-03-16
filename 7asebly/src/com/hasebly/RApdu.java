package com.hasebly;

abstract public class RApdu extends Apdu {
	
	String sw1,sw2;
	
	public RApdu(byte[] rawReturnedApdu)
	{
		for(int x = 5; x < rawReturnedApdu.length - 6; x++)
			command.add(rawReturnedApdu[x]);
		
		sw2 = returnHexValue(rawReturnedApdu[rawReturnedApdu.length -4]);
		sw1 = returnHexValue(rawReturnedApdu[rawReturnedApdu.length -5]);
	}
	
	public boolean isSuccessfull()
	{
		if((sw1 + sw2).equals("9000"))
			return true;
		return false;			
	}

}
