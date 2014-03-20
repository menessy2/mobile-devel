package com.hasebly;

import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class UserView {
	
	private UniPayInterface reader;
	private RequestHub requestHub;
	private MagneticCardData magData = null;
	private ChipCardData chipData = null;
	
	public UserView()
	{
		requestHub = new RequestHub();
		getencryptionkeys();
	}

	public void initializeReader(Context usercontext)
	{
		reader = new UniPayInterface(usercontext);
		configureReader(usercontext);
	}
	

	public void destoryReader()
	{
		reader.destroyReader();
	}

	public String Login(String userName, String password)
	{
		EncryptionVault.setAesKey();
		RequestHandler handler  = new RequestHandler(requestHub.loginRequest(userName, password));
		if(handler.isSuccessful())
		{
			EncryptionVault.setSessionValue(handler.getResponceVariables().get("xvalue"));
			return "Sucess";
		}
		else
			return handler.getResponceVariables().get("reason");
	}
	
	public boolean getMagData()
	{
		chipData  = null;
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Callable<byte[]> temp = new SingleInstructionSender(reader,2) {
		};
		Future<byte[]> future = executor.submit(temp);
		try {
			String check = new String(future.get());
			if(check.contains("?;"))
			{
				magData = new MagneticCardData(new String(future.get()));
				return true;
			}
			return false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean getchipData()
	{
		magData = null;
		return false;
	}
	
	
	public String makeTransactionWithCode(String code)
	{
		if(magData !=null)
		{
			RequestHandler handler  = new RequestHandler(requestHub.magneticTransaction(magData.getDisplayName(), 
					magData.getPan(), magData.getExpiryDate(), magData.getSignature(), magData.getTrackOne(), magData.getTrackTwo(), code));
			if(handler.isSuccessful())
				return "Sucess";
			else
				return handler.getResponceVariables().get("reason");
		}
		else if(chipData != null)
		{
			RequestHandler handler  = new RequestHandler(requestHub.chipTransaction(chipData.getDisplayName(), 
					chipData.getPan(), chipData.getExpiryDate(), chipData.getSignature(),chipData.getTrackTwo(),
					chipData.getIccData(), code));
			if(handler.isSuccessful())
				return "Sucess";
			else
				return handler.getResponceVariables().get("reason");
		}
		else
			return "No credit card data available";
	}
	
	
	public String makeCashTransactionWithCode(String code, String mPin)
	{
		RequestHandler handler  = new RequestHandler(requestHub.cashTransaction(code, mPin));
		if(handler.isSuccessful())
			return "Sucess";
		else
			return handler.getResponceVariables().get("reason");
	}
	

	public String makeTransaction(double amount,String recipient)
	{
		if(magData !=null)
		{
			RequestHandler handler  = new RequestHandler(requestHub.explicitMagneticTransaction(magData.getDisplayName(), 
					magData.getPan(), magData.getExpiryDate(),amount, magData.getSignature(), magData.getTrackOne(), magData.getTrackTwo(), recipient));
			if(handler.isSuccessful())
				return "Sucess";
			else
				return handler.getResponceVariables().get("reason");
		}
		else if(chipData != null)
		{
			RequestHandler handler  = new RequestHandler(requestHub.explicitChipTransaction(chipData.getDisplayName(), 
					chipData.getPan(), chipData.getExpiryDate(),amount, chipData.getSignature(),chipData.getTrackTwo(),
					chipData.getIccData(), recipient));
			if(handler.isSuccessful())
				return "Sucess";
			else
				return handler.getResponceVariables().get("reason");
		}
		else
			return "No credit card data available";
	}
	
	
	public String makeCashTransaction(String recipient ,double amount, String mPin)
	{
		RequestHandler handler  = new RequestHandler(requestHub.explicitCashTransaction(recipient,amount,mPin));
		if(handler.isSuccessful())
			return "Sucess";
		else
			return handler.getResponceVariables().get("reason");
	}
	
	public HashMap<String, String> getCodeInfo(String code)
	{
		RequestHandler handler  = new RequestHandler(requestHub.getInformationFromCode(code));
		return handler.getResponceVariables();
	}
	
	public String changeCodeStatus(String code, String update)
	{
		RequestHandler handler  = new RequestHandler(requestHub.changeCodeStatus(code, update));
		if(handler.isSuccessful())
			return "Sucess";
		else
			return handler.getResponceVariables().get("reason");
	}
	
	
	
/*************************************helper methods**************************************************/
	
	private boolean getencryptionkeys() {
		RequestHandler handler  = new RequestHandler(requestHub.sendEncryptionRequest());
		if(handler.isSuccessful())
		{
			EncryptionVault.setRsaKey(handler.getResponceVariables().get("modulus"),
					handler.getResponceVariables().get("exponent"));
			return true;
		}
		else
			return false;
	}
	
	
	private void configureReader(Context userContext) {
		SharedPreferences sharedPreferences = userContext.getSharedPreferences("Reader_Setings_7aseblySDK", userContext.MODE_PRIVATE);
		if(sharedPreferences.contains("frequenceOutput"))
		{
			reader.bindWithSettings(sharedPreferences);
		}
		else
		{
			RequestHandler handler  = new RequestHandler(requestHub.checkPhoneSupport());
			if(handler.isSuccessful())
			{
				Editor editor = sharedPreferences.edit();
				editor.putInt("directionOutputWave",Integer.parseInt(handler.getResponceVariables().get("directionOutputWave")));
				editor.putInt("frequenceInput",Integer.parseInt(handler.getResponceVariables().get("frequenceInput")));
				editor.putInt("frequenceOutput",Integer.parseInt(handler.getResponceVariables().get("frequenceOutput")));
				editor.putInt("recordBufferSize",Integer.parseInt(handler.getResponceVariables().get("recordBufferSize")));
				editor.putInt("recordReadBufferSize",Integer.parseInt(handler.getResponceVariables().get("recordReadBufferSize")));
				editor.putInt("waveDirection",Integer.parseInt(handler.getResponceVariables().get("waveDirection")));
				editor.putInt("highThreshold",Integer.parseInt(handler.getResponceVariables().get("highThreshold")));
				editor.putInt("lowThreshold",Integer.parseInt(handler.getResponceVariables().get("lowThreshold")));
				editor.putInt("min",Integer.parseInt(handler.getResponceVariables().get("min")));
				editor.putInt("max",Integer.parseInt(handler.getResponceVariables().get("max")));
				editor.putInt("baudRate",Integer.parseInt(handler.getResponceVariables().get("baudRate")));
				editor.putInt("preAmbleFactor",Integer.parseInt(handler.getResponceVariables().get("preAmbleFactor")));
				editor.putInt("shuttleChannel",Integer.parseInt(handler.getResponceVariables().get("shuttleChannel")));
				editor.putInt("forceHeadsetPlug",Integer.parseInt(handler.getResponceVariables().get("forceHeadsetPlug")));
				editor.putInt("useVoiceRecognition",Integer.parseInt(handler.getResponceVariables().get("useVoiceRecognition")));
				editor.putInt("volumeLevelAdjust",Integer.parseInt(handler.getResponceVariables().get("volumeLevelAdjust")));
				editor.commit();
			}
			else 
			{
				if(handler.getResponceVariables().get("reason").equals("not found"))
				{
					
				}
				
					
					//yalahwy
			}
		}
		
		
	}
	
}
