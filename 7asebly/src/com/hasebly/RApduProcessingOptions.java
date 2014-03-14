package com.hasebly;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;



public class RApduProcessingOptions extends RApdu {
 
	//TODO: put definitions if needed :D
	byte tag; 
	ArrayList<Byte> aip;
	ArrayList<Byte> uselessInformation;
	ArrayList<Byte> processInfromation;
	

	public RApduProcessingOptions(byte[] rawReturnedApdu) {
		super(rawReturnedApdu);
		
		if(isSuccessfull())
		{
			aip = new ArrayList<Byte>();
			processInfromation = new ArrayList<Byte>();
			tag = command.get(0);
			aip.add(command.get(2));
			aip.add(command.get(3));
			int start = 4;
			if(tag == (byte)0x77)
			{
				 uselessInformation = new ArrayList<Byte>();
				 for(int x = 4; x < 8 ; x++)
				 {
					 uselessInformation.add(command.get(x));
				 }
				 start+= 4;
			}
			
			for(int x = start; x < command.size(); x++)
				processInfromation.add(command.get(x));
		}
	}
	
	public Queue<byte[]> populateReadingQue()
	{
		Queue<byte[]> readingQue = new PriorityQueue<byte[]>();
		for(int x = 0; x < processInfromation.size() ; x+=4)
		{
			byte[] infoTuple = new byte[2];
			infoTuple[0] = processInfromation.get(x);
			
			for(int y = byteToInt(processInfromation.get(x+1)); y <= byteToInt(processInfromation.get(x+2)); x++)
			{
				infoTuple[1] = (byte)y;
				readingQue.add(infoTuple);
			}					
			
		}		
		return readingQue;		
	}
	
	

}
