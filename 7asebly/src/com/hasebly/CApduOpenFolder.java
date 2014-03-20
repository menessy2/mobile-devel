package com.hasebly;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CApduOpenFolder extends CApdu<Boolean>{

	
	private static final String[] EMV_AIDS= { "A0000000031010", "A0000000032010","A0000000032020","A0000000038010","A0000000041010"
		,"A0000000049999","A0000000043060","A0000000046000","A0000000050001","A00000002501","A0000000291010","A0000000421010",
		"A0000000422010","A0000000651010","A0000001211010","A0000001410001","A0000001523010","A0000001544442","A00000022820101010",
		"A0000002771010","A0000003241010","A000000333010101","A000000333010102","A000000333010103","A000000333010106"
		,"A0000003591010028001","A00000035910100380","A0000003710001","A0000004391010","A0000005241010","A0000004320001"};
	private static final String BASE = "00A4040007";
	
	
	public CApduOpenFolder()
	{
		super();
	}
	
	@Override
	protected Boolean execute(UniPayInterface reader) {
		for(int x= 0; x < EMV_AIDS.length; x++)
		{
			command = returnListFromString(BASE + EMV_AIDS[x]);
			if(singleInstruction(command,reader))
				return true;
		}
		return false;
	}

	private boolean singleInstruction(ArrayList<Byte> command, UniPayInterface reader) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Callable<byte[]> temp = new SingleInstructionSender(reader,command) {};
		Future<byte[]> future = executor.submit(temp);
		try {
			RApduGeneric check = new RApduGeneric(future.get());
			return check.isSuccessfull();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	
	

	
}
