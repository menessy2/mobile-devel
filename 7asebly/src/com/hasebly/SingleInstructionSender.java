package com.hasebly;

import java.util.concurrent.Callable;


abstract public class SingleInstructionSender implements Callable<byte[]> {
	
	UniPayInterface reader;
	
    public SingleInstructionSender(UniPayInterface reader){
    	this.reader = reader;
    }

}
