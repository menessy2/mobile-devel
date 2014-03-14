package com.hasebly;

abstract public class CardData<inputType> {
	
	private String expiryDate, pan, name;
	private String publicPan, displayName;
	
	

	public CardData(String expiryDate, String pan, String name){
		setExpiryDate(expiryDate);
		setPan(pan);
		setName(name);
	}
	
	public CardData(){}

	public String getPublicPan() {
		return publicPan;
	}

	public String getDisplayName() {
		return displayName;
	}
	
	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
		publicPan = "************" + pan.substring(pan.length()-5);
		
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		if(name.contains("/"))
		{
			String[] seperatedNames = name.split("/"); 
			displayName = seperatedNames[1] + seperatedNames[0];
			while(displayName.contains("  "))
				displayName.replaceAll("  ", " ");
		}
		else
			displayName = name;
	}


	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	abstract protected String getExpiryFromData(inputType Data);
	abstract protected String getNameFromData(inputType Data);
	abstract protected String getPanFromData(inputType Data);
	//abstract protected String getTrackTwoFromData(inputType Data);	
}
