package com.hasebly;

import java.text.NumberFormat;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Build;
public class RequestHub {
	
	
	protected JSONObject checkPhoneSupport() 
	{
		String model =  Build.MANUFACTURER + " " + Build.MODEL;
		HashMap<String, String> clearVaribaes = new HashMap<String, String>();
		clearVaribaes.put("model",model);
		AsyncRequestSender temp = (AsyncRequestSender) new AsyncRequestSender().execute( // get the correct url from menesy
				"https://account.7asebly.com/sphere/make_transaction",Encryptor.encryptRequestVariables(clearVaribaes, Encryptor.AES));
		try {
			return new JSONObject(temp.responce);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}	
	
	
	protected JSONObject addnewphone(String directionOutputWave, String frequenceInput, String frequenceOutput,
			String recordBufferSize, String recordReadBufferSize, String waveDirection, String highThreshold,
			String lowThreshold, String min, String max, String baudRate, String preAmbleFactor, String shuttleChannel,
			String forceHeadsetPlug, String useVoiceRecognition, String volumeLevelAdjust)  
	{
		HashMap<String, String> clearVaribaes = new HashMap<String, String>();
		clearVaribaes.put("directionOutputWave",directionOutputWave);
		clearVaribaes.put("frequenceInput",frequenceInput);
		clearVaribaes.put("frequenceOutput",frequenceOutput);
		clearVaribaes.put("recordBufferSize",recordBufferSize);
		clearVaribaes.put("recordReadBufferSize",recordReadBufferSize);
		clearVaribaes.put("waveDirection",waveDirection);
		clearVaribaes.put("highThreshold",highThreshold);
		clearVaribaes.put("lowThreshold",lowThreshold);
		clearVaribaes.put("min",min);
		clearVaribaes.put("max",max);
		clearVaribaes.put("baudRate",baudRate);
		clearVaribaes.put("preAmbleFactor",preAmbleFactor);
		clearVaribaes.put("shuttleChannel",shuttleChannel);
		clearVaribaes.put("forceHeadsetPlug",forceHeadsetPlug);
		clearVaribaes.put("useVoiceRecognition",useVoiceRecognition);
		clearVaribaes.put("volumeLevelAdjust",volumeLevelAdjust);
		AsyncRequestSender temp = (AsyncRequestSender) new AsyncRequestSender().execute( // get the correct url from menesy
				"https://account.7asebly.com/sphere/make_transaction",Encryptor.encryptRequestVariables(clearVaribaes, Encryptor.AES));
		try {
			return new JSONObject(temp.responce);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	protected JSONObject sendEncryptionRequest()  
	{
		AsyncRequestSender temp = (AsyncRequestSender) new AsyncRequestSender().execute("https://account.7asebly.com/sphere/request");
		try {
			return new JSONObject(temp.responce);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	protected JSONObject loginRequest(String userName, String password)  
	{
		HashMap<String, String> clearVaribaes = new HashMap<String, String>();
		clearVaribaes.put("username",userName);
		clearVaribaes.put("password", password);
		clearVaribaes.put("x", EncryptionVault.getAesKey());
		AsyncRequestSender temp = (AsyncRequestSender) new AsyncRequestSender().execute(
				"https://account.7asebly.com/sphere/login",Encryptor.encryptRequestVariables(clearVaribaes, Encryptor.RSA));
		try {
			return new JSONObject(temp.responce);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	protected JSONObject magneticTransaction(String cardHolder, String cardNumber, String expiryDate, 
			double amount, String signature, String trackOne, String trackTwo, String code)  
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
		try {
			return new JSONObject(temp.responce);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	protected JSONObject chipTransaction(String cardHolder, String cardNumber, String expiryDate, 
			double amount, String signature,String trackTwo, String iccData, String code)  
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
		try {
			return new JSONObject(temp.responce);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	protected JSONObject cashTransaction(String code, double amount, String cvv)  
	{
		HashMap<String, String> clearVaribaes = new HashMap<String, String>();
		clearVaribaes.put("a",toCurrencyString(amount));
		clearVaribaes.put("image",cvv);
		clearVaribaes.put("code",code);
		clearVaribaes.put("xvalue", EncryptionVault.getSessionValue());
		AsyncRequestSender temp = (AsyncRequestSender) new AsyncRequestSender().execute(
				"https://account.7asebly.com/sphere/make_transaction",Encryptor.encryptRequestVariables(clearVaribaes, Encryptor.AES));
		try {
			return new JSONObject(temp.responce);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	protected JSONObject explicitMagneticTransaction(String cardHolder, String cardNumber, String expiryDate, 
			double amount, String signature, String trackOne, String trackTwo, String recipient)  
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
		try {
			return new JSONObject(temp.responce);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	protected JSONObject explicitChipTransaction(String cardHolder, String cardNumber, String expiryDate, 
			double amount, String signature,String trackTwo, String iccData, String recipient)  
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
		try {
			return new JSONObject(temp.responce);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	protected JSONObject explicitCashTransaction(String recipient, double amount, String cvv)  
	{
		HashMap<String, String> clearVaribaes = new HashMap<String, String>();
		clearVaribaes.put("a",toCurrencyString(amount));
		clearVaribaes.put("image",cvv);
		clearVaribaes.put("recipient",recipient);
		clearVaribaes.put("xvalue", EncryptionVault.getSessionValue());
		AsyncRequestSender temp = (AsyncRequestSender) new AsyncRequestSender().execute(
				"https://account.7asebly.com/sphere/make_transaction",Encryptor.encryptRequestVariables(clearVaribaes, Encryptor.AES));
		try {
			return new JSONObject(temp.responce);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	protected JSONObject getInformationFromCode(String code)  
	{
		HashMap<String, String> clearVaribaes = new HashMap<String, String>();
		clearVaribaes.put("code",code);
		clearVaribaes.put("xvalue", EncryptionVault.getSessionValue());
		AsyncRequestSender temp = (AsyncRequestSender) new AsyncRequestSender().execute(
				"https://account.7asebly.com/sphere/get_transaction_details",Encryptor.encryptRequestVariables(clearVaribaes, Encryptor.AES));
		try {
			return new JSONObject(temp.responce);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	protected JSONObject changeCodeStatus(String code, String update)  
	{
		HashMap<String, String> clearVaribaes = new HashMap<String, String>();
		clearVaribaes.put("code",code);
		clearVaribaes.put("status",update);
		clearVaribaes.put("xvalue", EncryptionVault.getSessionValue());
		AsyncRequestSender temp = (AsyncRequestSender) new AsyncRequestSender().execute(
				"https://account.7asebly.com/sphere/order_completed",Encryptor.encryptRequestVariables(clearVaribaes, Encryptor.AES));
		try {
			return new JSONObject(temp.responce);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
		
	private String toCurrencyString(double amount)
	{
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		return formatter.format(amount);
	}
	
}
