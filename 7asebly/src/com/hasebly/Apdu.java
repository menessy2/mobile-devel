package com.hasebly;

import java.util.ArrayList;
import java.util.Locale;

abstract class Apdu{ 	

	protected ArrayList<Byte> command;
	protected int position = 0;
	
	/* This function will convert a byte at a certain position to hex. We & it 0xff 
 	   bytes in java are signed and we need to get the two's complement for it. */
	
	protected int returnIntValue(int arrayPosition)
	{
		return command.get(arrayPosition) & 0xff; 	 	 	
	}	
	
	public String returnHexValue(int arrayPosition)
	{
		String temp = Integer.toHexString(command.get(arrayPosition) & 0xff).toUpperCase(Locale.ENGLISH);
		return  temp;
	}
	public byte returnByteValue(int arrayPosition)
	{
		return command.get(arrayPosition);
	}
	
	public String returnHexValue(byte inputByte)
	{
		return Integer.toHexString(inputByte & 0xff).toUpperCase(Locale.ENGLISH); 
	}
	
	public String returnHexValue() 
	{
		StringBuilder apduString = new StringBuilder();
		for( int x = 0; x < command.size(); x++)
			if(returnHexValue(command.get(x)).length() == 1)
				apduString.append("0" + returnHexValue(command.get(x))); 
			else
				apduString.append(returnHexValue(command.get(x)));		
		return new String(apduString);
	}
	
	public String returnHexValue(String separator)
	{
		StringBuilder apduString= new StringBuilder();
		for( int x = 0; x < command.size(); x++)
			if(returnHexValue(command.get(x)).length() == 1)
				apduString.append(separator + "0" + returnHexValue(command.get(x))); 
			else
				apduString.append(separator + returnHexValue(command.get(x)));
		if(separator != "")
			for(int x = 0; x < separator.length(); x++)
				apduString.deleteCharAt(0);
			
		return new String(apduString);
	}

	public String returnHexValue(ArrayList<Byte> miniList, String separator)
	{
		StringBuilder apduString = new StringBuilder();
			for( int x = 0; x < miniList.size(); x++)
				if(returnHexValue(miniList.get(x)).length() == 1)
					apduString.append(separator + "0" + returnHexValue(miniList.get(x))); 
				else
					apduString.append(separator + returnHexValue(miniList.get(x)));
			if(separator != "")
				for(int x = 0; x < separator.length(); x++)
					apduString.deleteCharAt(0);
				
			return new String(apduString);
	}
	
	protected ArrayList<Byte> returnListFromString(String inputByteString)
	{
		
		inputByteString = inputByteString.replaceAll(" ", "");
		//TODO: throw an exception if the string is not even 
		
		ArrayList<Byte> returnedCommandList = new ArrayList<Byte>();
		for(int x = 0; x < inputByteString.length(); x+=2)
		{
			returnedCommandList.add((byte)Integer.parseInt(inputByteString.substring(x, x+2),16));
		} 	
		
		return returnedCommandList;
	}
	
	protected int byteToInt(byte signedByte)
	{
		return signedByte & 0xff;
	}

}
