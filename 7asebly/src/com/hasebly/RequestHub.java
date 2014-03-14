package com.hasebly;

import java.text.NumberFormat;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;
public class RequestHub {
	
	protected JSONObject sendEncryptionReques() throws JSONException
	{
		AsyncRequestSender temp = (AsyncRequestSender) new AsyncRequestSender().execute("https://account.7asebly.com/sphere/request");
		JSONObject encryptionDetails = new JSONObject(temp.responce);
		return encryptionDetails;
	}
	
	protected JSONObject loginRequest(String userName, String password) throws JSONException
	{
		HashMap<String, String> clearVaribaes = new HashMap<String, String>();
		clearVaribaes.put("username",userName);
		clearVaribaes.put("password", password);
		clearVaribaes.put("x", EncryptionVault.getAesKey());
		AsyncRequestSender temp = (AsyncRequestSender) new AsyncRequestSender().execute(
				"https://account.7asebly.com/sphere/login",Encryptor.encryptRequestVariables(clearVaribaes, Encryptor.RSA));
		JSONObject loginReply = new JSONObject(temp.responce);
		return loginReply;
	}

	protected JSONObject pureMagneticTransaction(String cardHolder, String cardNumber, String expiryDate, 
			double amount, String signature, String trackOne, String trackTwo) throws JSONException
	{
		HashMap<String, String> clearVaribaes = new HashMap<String, String>();
		clearVaribaes.put("ch",cardHolder);
		clearVaribaes.put("cn",cardNumber);
		clearVaribaes.put("ed",expiryDate);
		clearVaribaes.put("a",toCurrencyString(amount));
		clearVaribaes.put("image",signature);
		clearVaribaes.put("t1",trackOne);
		clearVaribaes.put("t2",trackTwo);
		clearVaribaes.put("xvalue", EncryptionVault.getSessionValue());
		AsyncRequestSender temp = (AsyncRequestSender) new AsyncRequestSender().execute(
				"https://account.7asebly.com/sphere/make_transaction",Encryptor.encryptRequestVariables(clearVaribaes, Encryptor.AES));
		return new JSONObject(temp.responce);
	}
	
	private String toCurrencyString(double amount)
	{
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		return formatter.format(amount);
	}
	
}
