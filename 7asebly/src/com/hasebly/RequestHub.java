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

	protected JSONObject magneticTransaction(String cardHolder, String cardNumber, String expiryDate, 
			double amount, String signature, String trackOne, String trackTwo, String code) throws JSONException
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
		if(code != null)
			clearVaribaes.put("code",code);
		AsyncRequestSender temp = (AsyncRequestSender) new AsyncRequestSender().execute(
				"https://account.7asebly.com/sphere/make_transaction",Encryptor.encryptRequestVariables(clearVaribaes, Encryptor.AES));
		return new JSONObject(temp.responce);
	}
	
	
	protected JSONObject chipTransaction(String cardHolder, String cardNumber, String expiryDate, 
			double amount, String signature,String trackTwo, String iccData, String code) throws JSONException
	{
		HashMap<String, String> clearVaribaes = new HashMap<String, String>();
		clearVaribaes.put("ch",cardHolder);
		clearVaribaes.put("cn",cardNumber);
		clearVaribaes.put("ed",expiryDate);
		clearVaribaes.put("a",toCurrencyString(amount));
		clearVaribaes.put("image",signature);
		clearVaribaes.put("t2",trackTwo);
		clearVaribaes.put("icc",iccData);		
		clearVaribaes.put("xvalue", EncryptionVault.getSessionValue());
		if(code != null)
			clearVaribaes.put("code",code);
		AsyncRequestSender temp = (AsyncRequestSender) new AsyncRequestSender().execute(
				"https://account.7asebly.com/sphere/make_chip_transaction",Encryptor.encryptRequestVariables(clearVaribaes, Encryptor.AES));
		return new JSONObject(temp.responce);
	}
	
	protected JSONObject cashTransaction(String code, double amount, String cvv) throws JSONException
	{
		HashMap<String, String> clearVaribaes = new HashMap<String, String>();
		clearVaribaes.put("a",toCurrencyString(amount));
		clearVaribaes.put("image",cvv);
		clearVaribaes.put("code",code);
		clearVaribaes.put("xvalue", EncryptionVault.getSessionValue());
		AsyncRequestSender temp = (AsyncRequestSender) new AsyncRequestSender().execute(
				"https://account.7asebly.com/sphere/make_transaction",Encryptor.encryptRequestVariables(clearVaribaes, Encryptor.AES));
		return new JSONObject(temp.responce);
	}
	
	protected JSONObject explicitMagneticTransaction(String cardHolder, String cardNumber, String expiryDate, 
			double amount, String signature, String trackOne, String trackTwo, String recipient) throws JSONException
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
		clearVaribaes.put("recipient", recipient);
		AsyncRequestSender temp = (AsyncRequestSender) new AsyncRequestSender().execute(
				"https://account.7asebly.com/sphere/make_transaction",Encryptor.encryptRequestVariables(clearVaribaes, Encryptor.AES));
		return new JSONObject(temp.responce);
	}
	
	
	protected JSONObject explicitChipTransaction(String cardHolder, String cardNumber, String expiryDate, 
			double amount, String signature,String trackTwo, String iccData, String recipient) throws JSONException
	{
		HashMap<String, String> clearVaribaes = new HashMap<String, String>();
		clearVaribaes.put("ch",cardHolder);
		clearVaribaes.put("cn",cardNumber);
		clearVaribaes.put("ed",expiryDate);
		clearVaribaes.put("a",toCurrencyString(amount));
		clearVaribaes.put("image",signature);
		clearVaribaes.put("t2",trackTwo);
		clearVaribaes.put("icc",iccData);		
		clearVaribaes.put("xvalue", EncryptionVault.getSessionValue());
		clearVaribaes.put("recipient", recipient);
		AsyncRequestSender temp = (AsyncRequestSender) new AsyncRequestSender().execute(
				"https://account.7asebly.com/sphere/make_chip_transaction",Encryptor.encryptRequestVariables(clearVaribaes, Encryptor.AES));
		return new JSONObject(temp.responce);
	}
	
	protected JSONObject explicitCashTransaction(String recipient, double amount, String cvv) throws JSONException
	{
		HashMap<String, String> clearVaribaes = new HashMap<String, String>();
		clearVaribaes.put("a",toCurrencyString(amount));
		clearVaribaes.put("image",cvv);
		clearVaribaes.put("recipient",recipient);
		clearVaribaes.put("xvalue", EncryptionVault.getSessionValue());
		AsyncRequestSender temp = (AsyncRequestSender) new AsyncRequestSender().execute(
				"https://account.7asebly.com/sphere/make_transaction",Encryptor.encryptRequestVariables(clearVaribaes, Encryptor.AES));
		return new JSONObject(temp.responce);
	}
	
	protected JSONObject getInformationFromCode(String code) throws JSONException
	{
		HashMap<String, String> clearVaribaes = new HashMap<String, String>();
		clearVaribaes.put("code",code);
		clearVaribaes.put("xvalue", EncryptionVault.getSessionValue());
		AsyncRequestSender temp = (AsyncRequestSender) new AsyncRequestSender().execute(
				"https://account.7asebly.com/sphere/get_transaction_details",Encryptor.encryptRequestVariables(clearVaribaes, Encryptor.AES));
		return new JSONObject(temp.responce);
	}
	
	protected JSONObject changeCodeStatus(String code, String update) throws JSONException
	{
		HashMap<String, String> clearVaribaes = new HashMap<String, String>();
		clearVaribaes.put("code",code);
		clearVaribaes.put("status",update);
		clearVaribaes.put("xvalue", EncryptionVault.getSessionValue());
		AsyncRequestSender temp = (AsyncRequestSender) new AsyncRequestSender().execute(
				"https://account.7asebly.com/sphere/order_completed",Encryptor.encryptRequestVariables(clearVaribaes, Encryptor.AES));
		return new JSONObject(temp.responce);	
	}
		
	private String toCurrencyString(double amount)
	{
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		return formatter.format(amount);
	}
	
}
