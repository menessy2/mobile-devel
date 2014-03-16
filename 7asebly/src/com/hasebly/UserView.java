package com.hasebly;


public class UserView {
	
	
	private RequestHub  requestHub;
	
	public UserView()
	{
		getencryptionkeys();
		requestHub = new RequestHub();
	}
	
	

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
	
	
}
