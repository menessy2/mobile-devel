package com.hasebly;


public class UserView implements DeviceInterface {
	
	
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

    ///// Below three functions are necessary for the callback functions
    ///// notice also that this calls is implementing an interface

    public void setupCallbackFunctions(){
        new UniPayInterface(this);
    }


    public void deviceConnected(){
        // action when device is connected
    }

    public void deviceDisconnected() {
        // action when device is disconnected
    }
}
