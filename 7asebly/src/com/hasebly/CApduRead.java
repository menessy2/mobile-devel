package com.hasebly;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CApduRead extends CApdu<RApduData>{
	
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

	@Override
	protected RApduData execute(UniPayInterface reader) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Callable<byte[]> temp = new SingleInstructionSender(reader,command) {
		};
		Future<byte[]> future = executor.submit(temp);
		try {
			RApduData check = new RApduData(future.get());
			if(check.isSuccessfull())
				return check;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
}
