package com.hasebly;

import java.util.ArrayList;

abstract public class CApdu extends Apdu {

	public CApdu()
	{
		super.command = new ArrayList<Byte>();
	}
	
	
	
}
