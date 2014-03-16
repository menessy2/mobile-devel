package com.hasebly;

abstract public class CardPresentData<inputType> extends CardData<inputType>{

	private String trackTwo;
	private String signature;
	
	public CardPresentData(String expiryDate, String pan, String trackTwo, String name){
		super(expiryDate,pan,name);		
		setTrackTwo(trackTwo);
	}
	
	
	protected String getSignature() {
		return signature;
	}


	protected void setSignature(String signature) {
		this.signature = signature;
	}


	public CardPresentData(){
		super();
	}
	
	public String getTrackTwo() {
		return trackTwo;
	}

	public void setTrackTwo(String trackTwo) {
		this.trackTwo = trackTwo;
	}

	abstract protected String getTrackTwoFromData(inputType Data);
}
