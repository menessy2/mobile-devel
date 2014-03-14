package com.hasebly;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import android.util.Log;

public class StorageApdu extends Apdu {

	String tag;
	String stringDataFormat1; 
	String stringDataAsciiFormat;
	
	
	public StorageApdu()
	{
		command = new ArrayList<Byte>();
		tag = "";
		stringDataAsciiFormat = "";
		stringDataFormat1 = "";
	}
	
	public StorageApdu(ArrayList<Byte> intializer, String tag)
	{
		command = new ArrayList<Byte>(intializer);
		setTag(tag);
		setStringDataAsciiFormat();
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getStringDataFormat1() {
		return stringDataFormat1;
	}

	public void setStringDataFormat1() {
		stringDataFormat1 = returnHexValue("");
	}

	public String getStringDataAsciiFormat() {
		return stringDataAsciiFormat;
	}

	public void setStringDataAsciiFormat() {
		byte[] tempCommandClone = new byte[command.size()];
		for(int x = 0; x < command.size(); x++)
				tempCommandClone[x] = command.get(x);
		try {
			stringDataAsciiFormat = new String(tempCommandClone,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			Log.e("Tag set Error", "Ecoding not supported");
			e.printStackTrace();
		}
	}
	
}
