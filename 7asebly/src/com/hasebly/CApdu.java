package com.hasebly;

import java.util.ArrayList;

abstract public class CApdu<ReturnType> extends Apdu {

	
	public CApdu()
	{
		super.command = new ArrayList<Byte>();
	}
	
	abstract protected ReturnType execute(UniPayInterface reader);
	
	
}
