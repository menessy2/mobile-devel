package com.hasebly;


public class UserView {
	
	
	private String username, password; 
	private RequestHub  requestHub;
	
	public UserView(String username, String Password)
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
		RequestHandler handler  = new RequestHandler(requestHub.loginRequest(userName, password));
		return password;
		
	}
	
	
}
