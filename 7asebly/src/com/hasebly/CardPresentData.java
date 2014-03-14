package com.hasebly;

abstract public class CardPresentData<inputType> extends CardData<inputType>{

	String trackTwo;
	
	public CardPresentData(String expiryDate, String pan, String trackTwo, String name){
		super(expiryDate,pan,name);		
		setTrackTwo(trackTwo);
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
