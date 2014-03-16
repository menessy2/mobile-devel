package com.hasebly;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import android.util.Base64;

/* Encryptor Guide 
 * 1 = RSA
 * 2 = AES
 * 
 * */ 



public class Encryptor {

	private static final byte[] IV ={0x66,0x75,0x63,0x6b,0x20,0x79,0x6f,0x75,0x20,0x62,0x69,0x74,0x63,0x68,0x65,0x73};
	public static final int RSA = 1;
	public static final int AES = 2;
	
	private static String encryptStringRSA(String clearString) 
	{		 
		String encrypted_data = null;
        Cipher cipher;
		try {
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, EncryptionVault.getRsaKey());
	        byte[] encrypted = cipher.doFinal(clearString.getBytes());
	        encrypted = Base64.encode(encrypted,Base64.DEFAULT);
	        encrypted_data = new String(encrypted);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return encrypted_data;
	}
	
	private static String encryptStringAES(String clearString)
	{
		byte [] encoddedkey = Base64.decode(EncryptionVault.getAesKey(),Base64.DEFAULT);
        IvParameterSpec ips = new IvParameterSpec(IV);
        String encrypted_data= null;
        Key Akey = new SecretKeySpec(encoddedkey, "AES");
        Cipher c;
		try {
			c = Cipher.getInstance("AES/CBC/PKCS5Padding");
			c.init(Cipher.ENCRYPT_MODE, Akey,ips);
	        byte[] encVal = c.doFinal(clearString.getBytes());
	        encrypted_data = Base64.encodeToString(encVal,Base64.DEFAULT);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encrypted_data;
        
	}
	
	
	public static String encryptRequestVariables(HashMap<String, String> variables, int encryptoinType)
	{
		StringBuilder variableString = new StringBuilder();
		Set<String> keys = variables.keySet();
		String xValue = null;
		String encrypted_data = null;
		
		if(variables.containsKey("xvlaue"))
		{
			xValue = variables.get("xvlaue");
			variables.remove("xvalue");
		}
		
		for (Iterator<String> i = keys.iterator(); i.hasNext();)
		{
		       String key = (String) i.next();
		       String value = (String) variables.get(key);
		       variableString.append("&" + key + "=" + value);
		       variableString.deleteCharAt(0);
		}
		
		if(encryptoinType == 1)
			encrypted_data = encryptStringRSA(new String(variableString));
		else if(encryptoinType == 2)		
			encrypted_data = encryptStringAES(new String(variableString));
		
		if(xValue != null)
			encrypted_data = xValue.substring(0,20) + encrypted_data + xValue.substring(20);

		return new String(variableString);
	}
	
	
}
