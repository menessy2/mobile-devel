package com.hasebly;

import java.util.ArrayList;
import java.util.List;
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
		return Integer.toHexString(inputByte & 0xff); 
	}
	
	public String returnHexValue() //TODO: check if hex value is not 2 characters
	{
		String apduString = "";
		for( int x = 0; x < command.size(); x++)
			apduString+= Integer.toHexString(command.get(x) & 0xff);		
		return apduString;
	}
	
	public String returnHexValue(String separator)
	{
		String apduString = "";
		for( int x = 0; x < command.size(); x++)
			apduString+= separator + Integer.toHexString(command.get(x) & 0xff);	
		return apduString.substring(apduString.indexOf(separator.charAt(separator.length()+1)));
	}

	public String returnHexValue(ArrayList<Byte> miniList, String separator)
	{
		String apduString = "";
		for( int x = 0; x < miniList.size(); x++)
			apduString+= separator + Integer.toHexString(miniList.get(x) & 0xff);	
		return apduString.substring(apduString.indexOf(separator.charAt(separator.length()+1)));
	}
	
	protected List<Byte> returnListFromString(String inputByteString)
	{
		
		inputByteString.replaceAll(" ", "");
		
		List<Byte> returnedCommandList = new ArrayList<Byte>();
		for(int x = 0; x < inputByteString.length(); x+=2)
		{
			returnedCommandList.add(Byte.parseByte(inputByteString.substring(x, x+2),16));
		} 	
		
		return returnedCommandList;
	}
	
	protected int byteToInt(byte signedByte)
	{
		return signedByte & 0xff;
	}
	
	
	
}
