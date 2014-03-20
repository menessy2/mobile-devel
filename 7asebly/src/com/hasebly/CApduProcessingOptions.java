package com.hasebly;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CApduProcessingOptions extends CApdu<RApduProcessingOptions>{
	
	public CApduProcessingOptions()
	{
		super();
		command = returnListFromString("80 A8 00 00 02 83 00");
	}

	@Override
	protected RApduProcessingOptions execute(UniPayInterface reader) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Callable<byte[]> temp = new SingleInstructionSender(reader,command) {
		};
		Future<byte[]> future = executor.submit(temp);
		try {
			RApduProcessingOptions check = new RApduProcessingOptions(future.get());
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
