package com.hasebly;

import java.util.ArrayList;
import java.util.concurrent.Callable;


abstract public class SingleInstructionSender implements Callable<byte[]> {
	
	private static final int AUTOCONFIG = 1;
	private static final int SEND_INSTRUCTION = 0;
	private static final int SWIPE_CARD = 2;
	private static final int POWER_ON_ICC = 3;
	
	
	
	private UniPayInterface reader;
	private int type;
	private ArrayList<Byte> instruction = null;
	
    public SingleInstructionSender(UniPayInterface reader, int type){
    	this.reader = reader;
    	this.type = type;
    }
    
    public SingleInstructionSender(UniPayInterface reader,ArrayList<Byte> instruction){
    	this.reader = reader;
    	this.instruction = instruction;
    	type = SEND_INSTRUCTION;
    }

	@Override
	public byte[] call() throws Exception {
		switch(type)
		{
		case AUTOCONFIG:
			reader.reader.startAutoConfig(true);
			wait();
			break;
		case SEND_INSTRUCTION:
			reader.sendAPDU(instruction);
			wait();
			break;		
		case SWIPE_CARD:
			reader.readMagneticStrip();
			wait();
			break;
		case POWER_ON_ICC:
			reader.reader.sendCommandPowerOnICC();
			wait();
			break;
		default:
			return null;
		}
		return reader.byteAnswer;
	}

    
}
